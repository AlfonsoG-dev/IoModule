import operations.FileOperations;
import operations.ExecutorOperation;

import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.concurrent.Callable;

public class IoModule {
    public static void main(String[] args) {
        FileOperations fo = new FileOperations();
        String searchPath = "D:/Descargas/dependencies/mysql-connector-j-9.1.0/";
        List<Path> files = new ArrayList<>();
        List<Callable<List<Path>>> callables = new ArrayList<>();

        ExecutorOperation ep = new ExecutorOperation();

        for(int i=0; i<args.length; ++i) {
            switch(args[i]) {
                case "--r": 
                    ep.executeRunnable(fo.runnableListFiles(Paths.get(searchPath), files), files);
                break;
                case "--c":
                    ep.executeCallable(fo.getCallableListOfFiles(searchPath));
                break;
                case "--ec":
                    ep.executorOfCallable(fo.getCallableListOfFiles(searchPath));
                break;
                case "--lc":
                    callables.add(fo.getCallableListOfFiles(searchPath));
                    callables.add(fo.getCallableListOfDirNames(searchPath));
                    ep.executorOfCallableList(callables);
                break;
                case "--rfl":
                    ep.executorOfCallable(fo.getCallableFileLines("src/application/IoModule.java"));
                break;
                case "--h":
                    System.out.println("Using the directory | " + searchPath + " |");
                    System.out.println("Use --r to get the list of files by Runnable");
                    System.out.println("Use --c to get the list of files by Callable");
                    System.out.println("Use --ec to get the list of files by Callable in Asynchronous");
                    System.out.println("Use --lc to get the computation of two callables");
                    System.out.println("Use --rfl to get the file lines of one file");
                break;
                default: 
                    System.out.println("use --h for help");
                break;
            }
        }
    }
}
