package org.example.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final Lock lock = new ReentrantLock();

    public  void  outerMethod(){
        lock.lock();
        try{
            System.out.println("outer method");
            innerMethod();
        }finally {
            lock.unlock();
        }
    }

    public  void  innerMethod(){
        lock.lock();
        try{
            System.out.println("inner method");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample lock1 = new ReentrantLockExample();
        lock1.outerMethod();
    }
}
