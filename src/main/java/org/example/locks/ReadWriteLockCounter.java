package org.example.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {
    private int count = 0;

    public final ReadWriteLock lock = new ReentrantReadWriteLock();
    public final Lock readLock = lock.readLock();
    public final Lock writeLock = lock.writeLock();

    public void increment(){
        writeLock.lock();
        try{
            count++;
            Thread.sleep(50); // CPU will give chance to execute other read thread in mean while
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount(){
        readLock.lock();
        try{
            return count;
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockCounter  counter =  new ReadWriteLockCounter();

        Runnable readTask =  new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    System.out.println(Thread.currentThread().getName() + " GetCount : " + counter.getCount());
                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    counter.increment();
                    System.out.println(Thread.currentThread().getName() + " Incremented count : ");
                }
            }
        };

        Thread writethread = new Thread(writeTask);
        Thread readThread1 = new Thread(readTask);
        Thread readThread2 = new Thread(readTask);

        writethread.start();
        readThread1.start();
        readThread2.start();

        writethread.join();
        readThread1.join();
        readThread2.join();

        System.out.println("Final Count : "+counter.getCount());
    }

}
