package src.java.school21.printer.app;

import java.io.File;

import src.java.school21.printer.logic.ImageConverter;

public class Program {
    public static char whiteChar;
    public static char blackChar;

    public static void main(String[] args) {
        if (args.length == 3 && args[0].length() == 1 && args[1].length() == 1) {
            whiteChar = args[0].charAt(0);
            blackChar = args[1].charAt(0);
            String pathImages = args[2];

            ImageConverter ic = new ImageConverter();
            ic.convertAndPrint(whiteChar, blackChar, new File(pathImages));
        }

    }


}
