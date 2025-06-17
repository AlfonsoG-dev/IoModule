import java.io.File;
import java.util.concurrent.Future;
class IoModule {
    public static void main(String[] args) {
        String localPath = "." + File.separator;
        FileOperations fo = new FileOperations(localPath);
        var callableList = fo.asyncListFiles(localPath + "src");
        try {
            callableList
                .call()
                .stream()
                .forEach(System.out::println);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
