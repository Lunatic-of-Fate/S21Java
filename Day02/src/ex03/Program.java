package ex03;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static ex03.FileUtility.*;

public class Program {
    public static void main(String[] args) {
        String[] inputArg = args[0].split("=");
        if(args.length != 1 || !inputArg[0].equals("--current-folder")) {
            System.out.println("file no such directory");
            return;
        }

        Path currentPath = Paths.get(inputArg[1]);

        Scanner scanner = new Scanner(System.in);
        String inputCommand;
        while(!(inputCommand = scanner.nextLine()).equals("exit")) {
            String[] inputArr = inputCommand.split(" ");
            switch (inputArr[0]) {
                case "mv":
                    moveFile(Paths.get(inputArr[1]), Paths.get(inputArr[2]));
                    break;
                case "cd":
                    currentPath = changeDirectory(currentPath, inputArr[1]);
                    System.out.println("/" + currentPath.getParent().getFileName() + "/" + currentPath.getFileName());
                    break;
                case "ls":
                    listFiles(currentPath);
                    break;
            }
        }
    }



}
//mv /home/user/documents/file.txt /home/user/desktop/