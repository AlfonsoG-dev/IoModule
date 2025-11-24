package application.operations;

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
import java.util.stream.Stream;

public class FileOperations {
    public List<Path> getDirectoryFiles(String dirPath) {
        List<Path> files = new ArrayList<>();
        try(Stream<Path> s = Files.walk(Paths.get(dirPath), FileVisitOption.FOLLOW_LINKS)) {
            files = s
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
        try(Stream<Path> s = Files.walk(Paths.get(dirPath), FileVisitOption.FOLLOW_LINKS)) {
            names = s
                .map(Path::toFile)
                .filter(p -> p.isDirectory() && countFiles(p) > 0)
                .toList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return names;
    }
    public Callable<List<Path>> getCallableListOfDirNames(String dirPath) {
        return () -> getDirectoryNames(dirPath).stream().map(File::toPath).toList();
    }
    public Callable<List<Path>> getCallableListOfFiles(String dirPath) {
        return () -> getDirectoryFiles(dirPath);
    }
    public Runnable runnableListFiles(Path dir, List<Path> files) {
        return () -> {
            try(Stream<Path> s = Files.walk(dir, FileVisitOption.FOLLOW_LINKS)) {
                files.addAll(s.toList());
            } catch(Exception e) {
                e.printStackTrace();
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
        return () -> getFileLines(filePath);
    }
}

