package org.example.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> System.out.println("Task After every 2 secs"),
                2,
                2,
                TimeUnit.SECONDS);
        scheduledExecutorService.schedule(
                () -> {
                    System.out.println("Initiating shutdown");
                    scheduledExecutorService.shutdown();
                },
                10,
                TimeUnit.SECONDS
        );
    }

}
