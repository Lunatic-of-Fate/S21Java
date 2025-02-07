package ex00;

import java.util.Objects;
import java.util.Scanner;

import static ex00.FileSignatureAnalyzer.fileSignatureAnalyzer;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFilePuth = "";

        while (!Objects.equals(inputFilePuth, "42")) {
            if((inputFilePuth = scanner.nextLine()).equals("42")) {
                return;
            }
            fileSignatureAnalyzer(inputFilePuth);
            System.out.println("PROCESSED");
        }
        scanner.close();
    }
}
