package org.example.executorService;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;

public class SingleThreadFuture {
    /**
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
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

        // isTerminated example
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        Future<Integer>  future3 = executorService3.submit(() -> 42);
        if(future3.isDone()){
            System.out.println("Task is done !");
        }
        System.out.println(future3.get());
        executorService3.shutdown();
        Thread.sleep(100);
        System.out.println(executorService3.isTerminated());


        //invokeAll
        ExecutorService executorService4 = Executors.newFixedThreadPool(2);
        Callable<Integer> callable1 = () -> {
            System.out.println("task 1 ");
            return 1;
        };
        Callable<Integer> callable2 = () ->{
            System.out.println("task 2");
            return 2;
        };
        Callable<Integer> callable3 = () -> {
            System.out.println("task 3");
            return 3;
        };
        List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);
        List<Future<Integer>> futures = executorService4.invokeAll(list);
        for (Future<Integer> future4 : futures){
            System.out.println(future4.get());
        }
        executorService4.shutdown();

    }
}
