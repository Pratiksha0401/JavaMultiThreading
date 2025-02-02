package org.example.executorService;

import java.util.Calendar;
import java.util.concurrent.*;

public class SingleThreadFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer>  future = executorService.submit(() -> 42);
        if(future.isDone()){
            System.out.println("Task is done !");
        }
        System.out.println(future.get());
        executorService.shutdown();

        // don't want to return we use runnable interface
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> System.out.println(42);
        Future<?>  future1 = executorService1.submit(runnable);
        if(future1.isDone()){
            System.out.println("Task is done !");
        }
        System.out.println(future1.get());
        executorService1.shutdown();

        //  want to return we use Callable interface
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = () -> 42;
        Future<?>  future2 = executorService2.submit(callable);
        System.out.println(future2.get());
        if(future2.isDone()){
            System.out.println("Task is done ! ");
        }
        executorService2.shutdown();
    }
}
