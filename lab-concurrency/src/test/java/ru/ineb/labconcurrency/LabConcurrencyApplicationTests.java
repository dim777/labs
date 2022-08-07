package ru.ineb.labconcurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LabConcurrencyApplicationTests {
    private static final Logger LOGGER = LogManager.getLogger(LabConcurrencyApplicationTests.class);

    boolean data;

    @Test
    public void singletonTests() throws InterruptedException {
        List<Thread> threads = IntStream.range(0, 10)
            .mapToObj(i -> {
                Thread t = new Thread(() -> {
                    Integer var = Singleton.getInstance().var;
                    LOGGER.info(var);
                });
                t.start();
                return t;
            }).toList();
        for (Thread thread : threads) {
            thread.join(1000);
        }
    }

    @Test
    public void testsFailure() throws InterruptedException {
        List<Thread> threads = IntStream.range(0, 100000)
            .mapToObj(i -> {
                Thread t = new Thread(() -> Singleton.getInstance().var++);
                t.start();
                return t;
            }).toList();
        for (Thread thread : threads) {
            thread.join();
        }
        LOGGER.info(Singleton.getInstance().var);
    }

    @Test
    public void testsSuccess() throws InterruptedException {
        List<Thread> threads = IntStream.range(0, 100000)
            .mapToObj(i -> {
                Thread t = new Thread(() -> {
                    synchronized (SingletonCorrect.class) {
                        int var = SingletonCorrect.getInstance().getVar();
                        var++;
                        SingletonCorrect.getInstance().setVar(var);
                    }
                });
                t.start();
                return t;
            }).toList();
        for (Thread thread : threads) {
            thread.join();
        }
        LOGGER.info(SingletonCorrect.getInstance().getVar());
    }

    public synchronized void waitForSmt() {
        while (!data) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testParallelStream() {
        List<String> arr = Arrays.asList("a", "b", "c", "d", "e");
        arr.parallelStream().forEach(LOGGER::info);
    }
}
