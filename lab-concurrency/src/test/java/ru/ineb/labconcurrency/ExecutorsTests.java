package ru.ineb.labconcurrency;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class ExecutorsTests {
    private static final Logger LOGGER = LogManager.getLogger(ExecutorsTests.class);

    // bad practise - plz do it only for exam preparation
    static Integer COUNTER = 0;

    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Test
    public void GivenSingleThreadExecutorThenImplementOrderingForOneThread() throws InterruptedException {

        final Future<?> f0 = executorService.submit(() -> {
            {
                for (int i = 0; i < 1_000_000; i++) {
                    COUNTER++;
                    if (i % 10000 == 0) {
                        LOGGER.info("Thread = {}, i = {}", Thread.currentThread(), i);
                    }
                }
            }
        });

        final Future<?> f1 = executorService.submit(() -> {
            {
                for (int i = 0; i < 1_000_000; i++) {
                    COUNTER++;
                    if (i % 10000 == 0) {
                        LOGGER.info("Thread = {}, i = {}", Thread.currentThread(), i);
                    }
                }
            }
        });

        do {
            LOGGER.info("Wait thread complete. Counter value = {}", COUNTER);
            //noinspection BusyWait
            Thread.sleep(5);
        } while (!f0.isDone() || !f1.isDone());

        Assert.assertEquals(2_000_000, COUNTER.intValue());
    }

    @Test
    public void GivenExecutorServiceThenFireAndForget() throws InterruptedException {
        //fire and forget
        executorService.execute(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                COUNTER++;
                if (i % 10000 == 0) {
                    LOGGER.info("Thread = {}, i = {}", Thread.currentThread(), i);
                }
            }
        });
        Assert.assertTrue(COUNTER < 1_000_000);

        do {
            LOGGER.info("Wait FireAndForget thread complete. Counter value = {}", COUNTER);
            //noinspection BusyWait
            Thread.sleep(5);
        } while (COUNTER != 1_000_000);
    }

    /**
     * Executes the given tasks, synchronously returning the results of all tasks as a Collection of Future objects,
     * in the same order they were in the original collection
     */
    @Test
    public void GivenExecutorServiceThenOrderingWith() {
        //TODO: maybe will write some scenario
        //<T> List<Future<T>> invokeAll( Collection<? extends Callable<T>> tasks) throws InterruptedException
    }

    public static void useCallable(Callable<Integer> expression) {
    }

    public static void useSupplier(Supplier<Integer> expression) {
    }

    public static void use(Callable<Integer> expression) {
    }

    public static void use(Supplier<Integer> expression) {
    }

    /**
     * Not @Test(expected = IOException.class) because we don't call callable
     */
    @Test
    public void GivenCallableAndSupplierWhenTryingToUseThemThenIts() {
        // COMPILES
        useCallable(() -> {
            // checked
            throw new IOException();
        });

        // DOES NOT COMPILE
//        useSupplier(() -> {
//            throw new IOException();
//        });

        // DOES NOT COMPILE
//        use(() -> {
//            throw new IOException();
//        });

    }

    @After
    public void deInit() {
        executorService.shutdown();
        COUNTER = 0;
    }
}