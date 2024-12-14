package org.example.locks;

class SharedResources{
    private int data;
    private boolean hasData;

    public synchronized int consume(){
        while (!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData =false;
        System.out.println("Consumed : "+data);
        notify();  // will use notifyAll if will have multiple consumers
        return data;
    }

    public synchronized void produce(int value){
        while (hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("produced : "+value);
        notify();  // will use notifyAll if will have multiple consumers
    }
}

class Consumer implements Runnable{
    private SharedResources resources;

    public Consumer(SharedResources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            resources.consume();
        }
    }
}

class Producer implements Runnable{
    private SharedResources resources;

    public Producer(SharedResources resources) {
        this.resources = resources;
    }
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            resources.produce(i);
        }
    }
}
public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResources sharedResources = new SharedResources();
        Thread consumerThread = new Thread(new Consumer(sharedResources));
        Thread producerThread = new Thread(new Producer(sharedResources));

        consumerThread.start();
        producerThread.start();
    }
}
