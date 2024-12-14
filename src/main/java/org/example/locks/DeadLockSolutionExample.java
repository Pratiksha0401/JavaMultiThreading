package org.example.locks;


class Pen1{
    public synchronized void writeWithPenAndPaper(Paper1 paper){
        System.out.println(Thread.currentThread().getName()+ " is using pen " + this+" trying to use paper "+ paper);
        paper.finishWriting();
    }

    public synchronized  void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ " finish using Pen "+this);
    }
}

class Paper1{
    public synchronized void writeWithPenAndPaper(Pen1 pen){
        System.out.println(Thread.currentThread().getName()+ " is using paper " + this+" trying to use pen "+ pen);
        pen.finishWriting();
    }

    public synchronized  void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ " finish using paper "+this);
    }
}

class Task11 implements Runnable{
    private Paper1 paper;
    private Pen1 pen;

    public Task11(Pen1 pen,Paper1 paper){
        this.pen=pen;
        this.paper=paper;
    }
    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); // thread1 locks pen tries to lock paper
    }
}

class Task22 implements Runnable{
    private Paper1 paper;
    private Pen1 pen;

    public Task22(Pen1 pen,Paper1 paper){
        this.pen=pen;
        this.paper=paper;
    }
    @Override
    public void run() {
        synchronized (pen){
            paper.writeWithPenAndPaper(pen);  // thread1 locks paper tries to lock pen
        }
    }
}

public class DeadLockSolutionExample {
    public static void main(String[] args) {
        Paper1 paper = new Paper1();
        Pen1 pen = new Pen1();
        Thread t1 =  new Thread(new Task11(pen,paper), "Thread-1");
        Thread t2 =  new Thread(new Task22(pen,paper), "Thread-2");
        t1.start();
        t2.start();
    }
}
