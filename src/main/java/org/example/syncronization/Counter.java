package org.example.syncronization;

public class Counter {
    private int count = 0;

    public int getCount(){
        return count;
    }

    public synchronized void increment(){
        count++;
    }
}
