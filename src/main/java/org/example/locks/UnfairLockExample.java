package org.example.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnfairLockExample {

    private final Lock unfairLock = new ReentrantLock(); // unfair locks means execution will be in random order
    private final Lock fairLock = new ReentrantLock(true); // fair locks means execution will be in specific  order decided by OS


    public void accessResource(){
        fairLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" Lock acquired !!");
            Thread.sleep(222);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName()+" unlock the lock");
            fairLock.unlock();
        }
    }

    public static void main(String[] args) {
        UnfairLockExample unfairLockExample = new UnfairLockExample();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                unfairLockExample.accessResource();
            }
        };

        Thread t1 = new Thread(runnable,"Thread 1");
        Thread t2 = new Thread(runnable,"Thread 2");
        Thread t3 = new Thread(runnable,"Thread 3");
        t1.start();
        t2.start();
        t3.start();
    }

}
