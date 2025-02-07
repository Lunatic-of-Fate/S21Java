package edu.school21.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private final int port;
    private final String hostname;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(hostname, port)) {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(reader.readLine());
            String response;
            String text;
            do {
                text = consoleReader.readLine();
                writer.println(text);
                response = reader.readLine();
                if (response != null) {
                    System.out.println(response);
                }
            } while (!response.equals("Successful!"));

        } catch (UnknownHostException e) {
            System.out.println("Server is not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
