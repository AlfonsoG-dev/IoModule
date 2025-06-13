
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileVisitOption;

public class FileOperations {
    private String localPath;
    public FileOperations(String localPath) {
        this.localPath = localPath;
    }
    public List<Path> getDirectoryFiles(String dirPath) {
        List<Path> files = new ArrayList<>();
        try {
            files = Files.walk(Paths.get(dirPath),  FileVisitOption.FOLLOW_LINKS)
                .toList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}

