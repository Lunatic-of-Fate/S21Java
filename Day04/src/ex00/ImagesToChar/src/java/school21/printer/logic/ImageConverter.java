package src.java.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {

    public void convertAndPrint(char whiteChar, char blackChar, File imagePath) {
        try {
            BufferedImage bufImage = ImageIO.read(imagePath);
            int width = bufImage.getWidth();
            int height = bufImage.getHeight();

            if (width != 16 || height != 16) {
                System.out.println("Image must be 16x16 pixels.");
                return;
            }
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int pixel = bufImage.getRGB(j, i);
                    if (pixel == Color.BLACK.getRGB()) {
                        System.out.print(blackChar);
                    }
                    if (pixel == Color.WHITE.getRGB()) {
                        System.out.print(whiteChar);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
