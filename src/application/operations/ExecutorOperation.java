package application.operations;

import java.io.Console;
import java.util.List;


import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;

public class ExecutorOperation {
    private static Console console = System.console();
    private static final String CONSOLE_FORMAT = "%s%n";


    public<T> void executeRunnable(Runnable r, List<T> result) {
        FutureTask<List<T>> futureRunnableTask = new FutureTask<>(r, result);

        if(!futureRunnableTask.isDone()) {
            console.printf(CONSOLE_FORMAT, "Waiting for results...");
            futureRunnableTask.run();
        }
        try {
            futureRunnableTask.get();
            if(!result.isEmpty()) {
                console.printf(CONSOLE_FORMAT, "Showing results");
                for(T rs: result) {
                    console.printf(CONSOLE_FORMAT, rs);
                }
            }
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    public<T> void executeCallable(Callable<List<T>> c) {
        FutureTask<List<T>> futureCallableList = new FutureTask<>(c);

        if(!futureCallableList.isDone()) {
            console.printf(CONSOLE_FORMAT, "Waiting for results...");
            futureCallableList.run();
        }
        try {
            List<T> result = futureCallableList.get();
            console.printf(CONSOLE_FORMAT, "Waiting to get the result...");
            if(!result.isEmpty()) {
                console.printf(CONSOLE_FORMAT, "Done computation showing results");
                for(T r: result) {
                    console.printf(CONSOLE_FORMAT, r);
                }
            }
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
    public<T> void executorOfCallable(Callable<List<T>> c) {
        FutureTask<List<T>> futureCallableList = new FutureTask<>(c);
        try(ExecutorService e = Executors.newSingleThreadExecutor()) {
            e.submit(futureCallableList);
            console.printf(CONSOLE_FORMAT, "Starting computation");
            // Esperar a que la tarea se complete
            List<T> result = futureCallableList.get();
            console.printf("Waiting to get the results...");
            if(!result.isEmpty()) {
                console.printf(CONSOLE_FORMAT, "Done computation showing results");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    public<T> void executorOfCallableList(List<Callable<List<T>>> taskList) {
        try(ExecutorService e = Executors.newCachedThreadPool()) {

            List<Future<List<T>>> futures = e.invokeAll(taskList);
            console.printf(CONSOLE_FORMAT, "Starting computation");

            // Process the results as each future completes
            for (Future<List<T>> future : futures) {
                if(!future.isDone()) {
                    console.printf(CONSOLE_FORMAT, "\t[Info] Wait for results...");
                    // Wait for and get the result (this blocks until the task finishes)
                }
                List<T> result = future.get();
                console.printf(CONSOLE_FORMAT, "\t[Info] Showing results...");
                if(future.isDone()) {
                    // Output the results
                    for (T r : result) {
                        console.printf(CONSOLE_FORMAT, r);
                    }
                }
            }
        } catch (InterruptedException | ExecutionException ex) {
            Thread.currentThread().interrupt();
            ex.printStackTrace();
        }
    }
    public<T> T completionOfCallable(Callable<T> task) {
        // give the completion service a executor thread with try_resource syntax
        try(ExecutorService executorThread = Executors.newFixedThreadPool(1)) {
            CompletionService<T> c = new ExecutorCompletionService<>(executorThread);
            Future<T> futureResult = c.submit(task);
            console.printf(CONSOLE_FORMAT, "[Info] Starting computation");
            if(!futureResult.isDone()) {
                console.printf(CONSOLE_FORMAT, "[Info] Waiting for results");
                c.take();
            }
            if(futureResult.isDone()) {
                return futureResult.get();
            }
        } catch(InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        return null;
    }
}
