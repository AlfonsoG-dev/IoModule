package operations;
import java.util.List;

import java.nio.file.Path;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;

public class ExecutorOperation {
    public void executeRunnableList(Runnable r, List<Path> result) {
        FutureTask<List<Path>> futureRunnableTask = new FutureTask<>(r, result);

        if(!futureRunnableTask.isCancelled()) {
            System.out.println("Starting computation");
            futureRunnableTask.run();
        }
        try {
            futureRunnableTask.get();
            System.out.println("Waiting to get the result...");
            if(futureRunnableTask.isDone()) {
                System.out.println("Done computation showing results");
                result
                    .stream()
                    .forEach(System.out::println);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void executeCallableList(Callable<List<Path>> c) {
        FutureTask<List<Path>> futureCallableList = new FutureTask<>(c);

        if(!futureCallableList.isCancelled()) {
            System.out.println("Starting computation");
            futureCallableList.run();
        }
        try {
            List<Path> result = futureCallableList.get();
            System.out.println("Waiting to get the result...");
            if(futureCallableList.isDone()) {
                System.out.println("Done computation showing results");
                result
                    .stream()
                    .forEach(System.out::println);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void executorCallableList(Callable<List<Path>> c) {
        FutureTask<List<Path>> futureCallableList = new FutureTask<>(c);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureCallableList);
        System.out.println("Starting computation");
        try {
            // Esperar a que la tarea se complete
            List<Path> result = futureCallableList.get();
            System.out.println("Waiting to get the results...");
            if(futureCallableList.isDone()) {
                System.out.println("Done computation showing results");
                result
                    .forEach(System.out::println);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
