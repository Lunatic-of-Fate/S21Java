package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {

    private final int port;
    private final UsersService usersService;

    public Server(@Value("${server.port}") int port, UsersService usersService) {
        this.port = port;
        this.usersService = usersService;
    }


    public void start() {
        while (true) {

            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server is listening on port " + port);
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                handleClient(socket);
            } catch (IOException e) {
                System.out.println("Server exception: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

    public void handleClient(Socket socket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello server");
            String message = in.readLine();
            if (message.equals("signUp")) {
                out.println("Enter username:");
                String login = in.readLine();
                out.println("Enter password:");
                String password = in.readLine();
                usersService.signUp(login, password);
                out.println("Successful!");
            } else {
                out.println("Unknown command");
            }
        } catch (IOException e) {
            System.out.println("Client handler exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

}
