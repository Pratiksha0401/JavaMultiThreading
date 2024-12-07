package org.example.locks;

public class Bank {
    public static void main(String[] args) {
        BankAccount sbi = new BankAccount();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    sbi.withDraw(100);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        t1.start();
        t2.start();

    }


}
