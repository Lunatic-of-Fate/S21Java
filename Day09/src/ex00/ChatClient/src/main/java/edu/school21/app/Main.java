package edu.school21.app;


import edu.school21.client.Client;
public class Main {
    public static void main(String[] args) {
        int port = 2345;
        for (String arg : args) {
            if (arg.startsWith("--port=")) {
                port = Integer.parseInt(arg.substring("--port=".length()));
            }
        }

        Client client = new Client("localhost", port);
        client.start();
    }
}