package application;

import application.operations.ExecutorOperation;
import application.operations.FileOperations;

import java.util.List;
import java.util.ArrayList;
import java.io.Console;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.concurrent.Callable;

public class IoModule {
    private static Console console = System.console();
    private static final String CONSOLE_FORMAT = "%s%n";

    public static void main(String[] args) {
        FileOperations fo = new FileOperations();
        String searchPath = args.length == 1 ? args[0] : "D:/Descargas/dependencies/mysql-connector-j-9.1.0/";
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
                List<String> r = ep.completionOfCallable(fo.getCallableFileLines("src/application/IoModule.java"));
                if(r != null && !r.isEmpty()) {
                    for(String s: r) {
                        console.printf(CONSOLE_FORMAT, s);
                    }
                }
                break;
                case "--h":
                    console.printf(CONSOLE_FORMAT, "Using the directory | " + searchPath + " |");
                    console.printf(CONSOLE_FORMAT, "Use --r to get the list of files by Runnable");
                    console.printf(CONSOLE_FORMAT, "Use --c to get the list of files by Callable");
                    console.printf(CONSOLE_FORMAT, "Use --ec to get the list of files by Callable in Asynchronous");
                    console.printf(CONSOLE_FORMAT, "Use --lc to get the computation of two callables");
                    console.printf(CONSOLE_FORMAT, "Use --rfl to get the file lines of one file");
                break;
                default: 
                    console.printf(CONSOLE_FORMAT, "use --h for help");
                break;
            }
        }
    }
}
