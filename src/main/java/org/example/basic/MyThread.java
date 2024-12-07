package org.example.basic;

public class MyThread extends Thread{
    // start, run, sleep , join, setPriority, interrupt , yield
//    @Override
//    public void run() {
//        try {
//            System.out.println("RUNNING");
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        MyThread t1 = new MyThread();
//        System.out.println(t1.getState());
//        t1.start();
//        System.out.println(t1.getState());
//        Thread.sleep(2000);
//        System.out.println(t1.getState());
//        t1.join();
//        System.out.println(t1.getState());
        /* output :
        NEW
        RUNNABLE
        RUNNING
        TIMED_WAITING
        TERMINATED
         */
//    }


    public MyThread(String name) {
        super(name);
    }

//    @Override
//    public void run() {
//        for (int i =0;i<=5;i++){
//            String a = "" ;
//            for(int j=0;j<=10000;j++){
//                a +=" ";
//            }
//            System.out.println(Thread.currentThread().getName() +" Priority : "+ Thread.currentThread().getPriority() + "  count :"+i);
//        }
//    }
//
//    public static void main(String[] args) {
//        MyThread l = new MyThread("LOW");  // need constructor to set name of thread like MYThread constructor
//        MyThread m = new MyThread("MEDIUM");
//        MyThread h = new MyThread("HIGH"); // high priority thread will execute 1st
//        l.start();
//        m.start();
//        h.start();
//
//    }


//    @Override
//    public void run() {
//        System.out.println("Thread is running");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            System.out.println("Tread is interrupted ..."+ e);
//        }
//    }
//
//    public static void main(String[] args) {
//        MyThread m1 = new MyThread("Thread 1");
//        m1.start();
//        m1.interrupt();
//        /*
//        o/p:
//        Thread is running
//        Tread is interrupted ...java.lang.InterruptedException: sleep interrupted
//         */
//    }

    @Override
    public void run() {
        for (int i = 0;i<=3;i++) {
            System.out.println(Thread.currentThread().getName() + "  is running ..");
            Thread.yield();  // giving permission to processor  to execute other thread
        }
    }

    public static void main(String[] args) {
        MyThread m1 = new MyThread("Thread 1");
        MyThread m2 = new MyThread("Thread 2");
        m1.start();
        m1.setDaemon(true); // this will run in background once main thread done it will stop execution of daemon thread as well
        m2.start();
        /*
        o/p:
        Thread 2  is running ..
        Thread 1  is running ..
        Thread 2  is running ..
        Thread 1  is running ..
        Thread 2  is running ..
        Thread 1  is running ..
        Thread 2  is running ..
        Thread 1  is running ..
         */
    }
}

