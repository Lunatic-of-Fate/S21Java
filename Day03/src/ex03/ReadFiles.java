package ex03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Queue;

public class ReadFiles {

    public static void readFiles(Queue<URI> queue) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ex03/files_urls"));
            String inputLine = br.readLine();
            while (inputLine != null) {
                String[] inputArr = inputLine.split(" ");
                try {
                    queue.add(new URI(inputArr[1]));
                } catch (URISyntaxException e) {
                    System.out.println(e);
                }
                inputLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
