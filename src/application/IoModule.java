import java.io.File;
class IoModule {
    public static void main(String[] args) {
        String localPath = "." + File.separator;
        FileOperations fo = new FileOperations(localPath);
        fo.getDirectoryNames("D:/Descargas/dependencies/mysql-connector-j-9.1.0/src/build/")
                .stream()
                .forEach(System.out::println);
    }
}
