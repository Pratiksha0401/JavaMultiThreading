package org.example.syncronization;

public class MyThread extends Thread{

    private  Counter counter;

    public MyThread(Counter counter){
        this.counter = counter;
    }
    @Override
    public void run() {
        for(int i =1;i<=1000;i++) {
            counter.increment();
        }
    }
}
