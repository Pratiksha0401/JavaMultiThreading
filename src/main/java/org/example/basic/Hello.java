package org.example.basic;

public class Hello implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<=100;i++){
            System.out.println("Hello !");
        }
    }
}
