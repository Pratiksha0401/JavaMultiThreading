package org.example.syncronization;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileAtomicExample {

    private volatile int counter = 0;
    AtomicInteger count = new AtomicInteger(1);

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementAtomic() {
        count.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileAtomicExample vc = new VolatileAtomicExample();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                vc.increment();
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 1000; i++) {
                vc.incrementAtomic();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Counter value: " + vc.getCounter());

        Thread t3 = new Thread(task2);
        Thread t4 = new Thread(task2);

        t3.start();
        t4.start();

        t3.join();
        t4.join();
        System.out.println("Atomic Counter value: " + vc.count);

    }
}


