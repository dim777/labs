package ru.ineb.labconcurrency;

import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureTests {
    private static final Logger LOGGER = LogManager.getLogger(CompletableFutureTests.class);

    @Test
    public void whenTest() throws InterruptedException, ExecutionException {
        //List<Tick> tickList = tickRepository.findAll();
        CompletableFuture<String> res0 = reportGet("");

        // Wait until they are all done
        CompletableFuture.allOf(res0).join();

        String r = res0.get();
        Runnable r0 = () -> {
            LOGGER.info(Thread.currentThread());
            LOGGER.info("OK");
        };

        // common scenario in ocp
        r0.run();

        final Thread thread = new Thread(r0);
        thread.start();

        Future<String> future = reportGet("");
        while (true) {
            if (future.isDone()) {
                System.out.println("Result from asynchronous process - " + future.get());
                break;
            }
            System.out.println("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }

    public static <T> CompletableFuture<String> reportGet(T t) throws InterruptedException {
        final String result = "OK";
        Thread.sleep(4000);
        return CompletableFuture.completedFuture(result);
    }
}
