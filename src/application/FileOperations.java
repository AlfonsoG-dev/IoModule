
import java.util.List;
import java.util.ArrayList;

import java.io.File;

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
    public int countFiles(File f) {
        int c = 0;
        if(f.listFiles() != null) {
            c = f.listFiles().length;
        }
        return c;
    }
    public List<File> getDirectoryNames(String dirPath) {
        List<File> names = new ArrayList<>();
        try {
            names = Files.walk(Paths.get(dirPath), FileVisitOption.FOLLOW_LINKS)
                .map(p -> p.toFile())
                .filter(p -> p.isDirectory() && countFiles(p) > 0)
                .toList();
        } catch(Exception e) {
        }
        return names;
    }
}

