package org.example.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 200;

    private Lock lock = new ReentrantLock();

//    public synchronized void withDraw(int amount) throws InterruptedException {
//        System.out.println(Thread.currentThread().getName()+" attempting to withdraw "+amount);
//        if(balance >= amount){
//            System.out.println(Thread.currentThread().getName()+ " proceeding to withdraw "+ amount);
//            Thread.sleep(2000);
//            balance -=amount;
//            System.out.println(Thread.currentThread().getName()+"  completing withdraw . Remaining balance "+balance);
//        }else {
//            System.out.println(Thread.currentThread().getName()+" insufficient balance "+balance);
//        }
//    }

    public void withDraw(int amount) {
        System.out.println(Thread.currentThread().getName()+" attempting to withdraw "+amount);
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if(balance >= amount){
                    System.out.println(Thread.currentThread().getName()+ " proceeding to withdraw "+ amount);
                    try {
                        Thread.sleep(2000);
                        balance -=amount;
                        System.out.println(Thread.currentThread().getName()+"  completing withdraw . Remaining balance "+balance);
                    }catch (Exception e){
                        Thread.currentThread().interrupt();
                    }finally {
                        lock.unlock();
                    }

                }else {
                    System.out.println(Thread.currentThread().getName()+" insufficient balance "+balance);
                }
            }else{
                System.out.println(Thread.currentThread().getName()+" could not acquire the lock !!!  remaining balance "+ balance);
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }

        if(Thread.currentThread().isInterrupted()){
            System.out.println("Yes current thread is interrupted");
        }
    }
}

/*
output with synchronized :
t1 attempting to withdraw 100
t1 proceeding to withdraw 100
t1  completing withdraw . Remaining balance 100
t2 attempting to withdraw 100
t2 proceeding to withdraw 100
t2  completing withdraw . Remaining balance 0


with locks:
t1 attempting to withdraw 100
t2 attempting to withdraw 100
t1 proceeding to withdraw 100
t2 could not acquire the lock !!!  remaining balance 200
t1  completing withdraw . Remaining balance 100
 */


