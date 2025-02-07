package ex03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtility {


    public static void moveFile(Path startPath, Path endPath) {
        try {
            Files.move(startPath, endPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void listFiles(Path currentPath) {
        File folder = currentPath.toFile();
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                String size = String.format("%.1f", sizeDirectory(file) / 1024) + " KB";
                System.out.println(file.getName() + " " + size);
            }
        }
    }

    public static Path changeDirectory(Path currentPath, String endPath) {
        Path newPath = currentPath.resolve(endPath);
        if (Files.isDirectory(newPath)) {
            currentPath = newPath;
            currentPath = currentPath.normalize();
        } else {
            System.out.println("file no such directory!");
        }
        return currentPath;
    }

    public static double sizeDirectory(File startFile) {
        double size = 0;
        if (startFile.isDirectory()) {
            File[] files = startFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        size += sizeDirectory(file);
                    } else {
                        size += file.length();
                    }
                }
            }
        } else {
            size += startFile.length();
        }
        return size;
    }
}