package org.example.basic;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        World world = new World(); //NEW
        world.start(); //RUNNABLE

        Hello hello = new Hello();
        Thread thread = new Thread(hello);
        thread.start();

        for (int i=0;i<=10;i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
