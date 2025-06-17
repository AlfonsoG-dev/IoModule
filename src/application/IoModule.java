
import java.util.List;
import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.FutureTask;
class IoModule {
    public static void main(String[] args) {
        String localPath = "." + File.separator;
        FileOperations fo = new FileOperations(localPath);
        String searchPath = "D:/Descargas/dependencies/mysql-connector-j-9.1.0/src";
        FutureTask<List<Path>> futureCallableList = new FutureTask<>(fo.getCallableListOfFiles(searchPath));
        List<Path> result = null;
        if(!futureCallableList.isCancelled()) {
            System.out.println("Running future task");
            futureCallableList.run();
        }
        try {
            if(futureCallableList.isDone()) {
                System.out.println("Done future task");
                result = futureCallableList.get();
            }
            if(result == null) {
                throw new Exception("No files found inside | " + searchPath + " |");
            }
            result
                .stream()
                .forEach(System.out::println);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
