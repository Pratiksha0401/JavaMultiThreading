package org.example.locks;


class Pen{
    public synchronized void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName()+ " is using pen " + this+" trying to use paper "+ paper);
        paper.finishWriting();
    }

    public synchronized  void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ " finish using Pen "+this);
    }
}

class Paper{
    public synchronized void writeWithPenAndPaper(Pen pen){
        System.out.println(Thread.currentThread().getName()+ " is using paper " + this+" trying to use pen "+ pen);
        pen.finishWriting();
    }

    public synchronized  void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ " finish using paper "+this);
    }
}

class Task1 implements Runnable{
    private Paper paper;
    private Pen pen;

    public Task1(Pen pen,Paper paper){
        this.pen=pen;
        this.paper=paper;
    }
    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); // thread1 locks pen tries to lock paper
    }
}

class Task2 implements Runnable{
    private Paper paper;
    private Pen pen;

    public Task2(Pen pen,Paper paper){
        this.pen=pen;
        this.paper=paper;
    }
    @Override
    public void run() {
        paper.writeWithPenAndPaper(pen);  // thread1 locks paper tries to lock pen
    }
}

public class DeadLockExample {
    public static void main(String[] args) {
        Paper paper = new Paper();
        Pen pen = new Pen();
        Thread t1 =  new Thread(new Task1(pen,paper), "Thread-1");
        Thread t2 =  new Thread(new Task2(pen,paper), "Thread-2");
        t1.start();
        t2.start();
    }
}
