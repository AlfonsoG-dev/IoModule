import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoModule {
    public static void main(String[] args) {
        String localPath = "." + File.separator;
        FileOperations fo = new FileOperations(localPath);
        String searchPath = "D:/Descargas/dependencies/mysql-connector-j-9.1.0/";
        List<Path> files = new ArrayList<>();

        ExecutorOperation ep = new ExecutorOperation(localPath);

        for(int i=0; i<args.length; ++i) {
            switch(args[i]) {
                case "r": 
                    ep.executeRunnableList(fo.runnableListFiles(Paths.get(searchPath), files), files);
                break;
                case "c":
                    ep.executeCallableList(fo.getCallableListOfFiles(searchPath));
                break;
                case "ec":
                    ep.executorCallableList(fo.getCallableListOfFiles(searchPath));
                break;
                case "--h":
                    System.out.println("Using the directory | " + searchPath + " |");
                    System.out.println("Use r to get the list of files by Runnable");
                    System.out.println("Use c to get the list of files by Callable");
                    System.out.println("Use ec to get the list of files by Callable in Asynchronous");
                break;
                default: 
                    System.out.println("use --h for help");
                break;
            }
        }
    }
}
