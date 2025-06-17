import java.io.File;
import java.util.concurrent.FutureTask;
class IoModule {
    public static void main(String[] args) {
        String localPath = "." + File.separator;
        FileOperations fo = new FileOperations(localPath);
        try {
            fo.getCallableListOfFiles(localPath + "src")
                .call()
                .stream()
                .forEach(System.out::println);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
