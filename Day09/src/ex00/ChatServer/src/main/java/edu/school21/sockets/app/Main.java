package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;


public class Main {
    public static void main(String[] args) {
        int port = 2345;
        for (String arg : args) {
            if (arg.startsWith("--port=")) {
                port = Integer.parseInt(arg.substring("--port=".length()));
            }
        }

        System.setProperty("server.port", String.valueOf(port));

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);

        Server server = context.getBean(Server.class);
        server.start();
    }
}