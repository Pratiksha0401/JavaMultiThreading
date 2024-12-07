package org.example.syncronization;

public class CounterWorld {
    public static void main(String[] args) throws InterruptedException {
        Counter counter =  new Counter();
        MyThread m1 = new MyThread(counter);
        MyThread m2 = new MyThread(counter);

        m1.start();
        m2.start();
        m1.join();
        m2.join();

        System.out.println(counter.getCount());
    }
}
