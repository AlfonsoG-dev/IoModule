import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoModule {
    public static void main(String[] args) {
        String localPath = "." + File.separator;
        FileOperations fo = new FileOperations(localPath);
        String searchPath = "D:/Descargas/dependencies/mysql-connector-j-9.1.0/src";
        List<Path> files = new ArrayList<>();

        ExecutorOperation ep = new ExecutorOperation(localPath);

        // ep.executeRunnableList(fo.runnableListFiles(Paths.get(searchPath), files), files);
        // ep.executeCallableList(fo.getCallableListOfFiles(searchPath));
        // ep.executorCallableList(fo.getCallableListOfFiles(searchPath));

    }
}
