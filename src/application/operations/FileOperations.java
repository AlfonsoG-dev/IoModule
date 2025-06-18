package operations;

import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileVisitOption;

import java.util.concurrent.Callable;

public class FileOperations {
    public List<Path> getDirectoryFiles(String dirPath) {
        List<Path> files = new ArrayList<>();
        try {
            files = Files.walk(Paths.get(dirPath), FileVisitOption.FOLLOW_LINKS)
                .filter(Files::isRegularFile)
                .toList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return files;
    }
    public int countFiles(File f) {
        File[] files = f.listFiles();
        return (files != null) ? files.length : 0;
    }
    public List<File> getDirectoryNames(String dirPath) {
        List<File> names = new ArrayList<>();
        try {
            names = Files.walk(Paths.get(dirPath), FileVisitOption.FOLLOW_LINKS)
                .map(p -> p.toFile())
                .filter(p -> p.isDirectory() && countFiles(p) > 0)
                .toList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return names;
    }
    public Callable<List<Path>> getCallableListOfDirNames(String dirPath) {
        return new Callable<List<Path>>() {
            public List<Path> call() {
                return getDirectoryNames(dirPath).stream().map(p -> p.toPath()).toList();
            }
        };
    }
    public Callable<List<Path>> getCallableListOfFiles(String dirPath) {
        return new Callable<List<Path>>() {
            public List<Path> call() {
                return getDirectoryFiles(dirPath);
            }
        };
    }
    public Runnable runnableListFiles(Path dir, List<Path> files) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Files.walk(dir, FileVisitOption.FOLLOW_LINKS)
                        .filter(Files::isRegularFile)
                        .forEach(p -> files.add(p));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
    public List<String> getFileLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public Callable<List<String>> getCallableFileLines(String filePath) {
        return new Callable<>() {
            @Override
            public List<String> call() {
                return getFileLines(filePath);
            }
        };
    }
}

