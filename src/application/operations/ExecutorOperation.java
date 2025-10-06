package operations;

import java.util.List;


import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Future.State;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;

public class ExecutorOperation {
    public<T> void executeRunnable(Runnable r, List<T> result) {
        FutureTask<List<T>> futureRunnableTask = new FutureTask<>(r, result);

        if(!futureRunnableTask.isCancelled()) {
            System.out.println("Starting computation");
            futureRunnableTask.run();
        }
        try {
            futureRunnableTask.get();
            System.out.println("Waiting to get the result...");
            if(result.size() > 0) {
                System.out.println("Done computation showing results");
                result
                    .stream()
                    .forEach(System.out::println);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public<T> void executeCallable(Callable<List<T>> c) {
        FutureTask<List<T>> futureCallableList = new FutureTask<>(c);

        if(!futureCallableList.isCancelled()) {
            System.out.println("Starting computation");
            futureCallableList.run();
        }
        try {
            List<T> result = futureCallableList.get();
            System.out.println("Waiting to get the result...");
            if(result.size() == 0) {
                System.out.println("Done computation showing results");
                result
                    .stream()
                    .forEach(System.out::println);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public<T> void executorOfCallable(Callable<List<T>> c) {
        FutureTask<List<T>> futureCallableList = new FutureTask<>(c);
        try(ExecutorService e = Executors.newSingleThreadExecutor()) {
            e.submit(futureCallableList);
            System.out.println("Starting computation");
            // Esperar a que la tarea se complete
            List<T> result = futureCallableList.get();
            System.out.println("Waiting to get the results...");
            if(result.size() > 0) {
                System.out.println("Done computation showing results");
                result
                    .forEach(System.out::println);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    public<T> void executorOfCallableList(List<Callable<List<T>>> taskList) {
        try(ExecutorService e = Executors.newCachedThreadPool()) {

            List<Future<List<T>>> futures = e.invokeAll(taskList);
            System.out.println("Starting computation");

            // Process the results as each future completes
            for (Future<List<T>> future : futures) {
                try {
                    if(future.isDone()) {
                        System.out.println("\t[Info] Wait for results...");
                        // Wait for and get the result (this blocks until the task finishes)
                        List<T> result = future.get();
                        // Output the results
                        System.out.println("\t[Info] Showing results...");
                        for (T r : result) {
                            System.out.println(r);
                        }
                    }
                } catch (ExecutionException ex) {
                    System.err.println("Task failed with an exception: " + ex.getCause());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.err.println("Task was interrupted");
                }
            }
        } catch (InterruptedException ex) {
            // Handle case where the entire operation is interrupted
            Thread.currentThread().interrupt();
            System.err.println("Execution was interrupted");
        }
    }
    public<T> T completionOfCallable(Callable<T> task) {
        // TODO: search on how to shutdown this process.
        T t = null;
        CompletionService<T> c = new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
        Future<T> futureResult = c.submit(task);
        try {
            System.out.println("[Info] Starting computation");
            try {
                if(futureResult.state() == State.RUNNING) {
                    System.out.println("[Info] Waiting for results");
                    c.take();
                }
                if(futureResult.state() == State.SUCCESS) {
                    t = futureResult.get();
                }
            } catch(ExecutionException e) {
                System.err.println("[Error] Execution fail");
                e.printStackTrace();
            }
        } catch(InterruptedException e) {
            System.err.println("[Error] Execution interrupted");
            e.printStackTrace();
        } finally {
            if(futureResult != null) {
                futureResult.cancel(true);
            }
        }
        return t;
    }
}
