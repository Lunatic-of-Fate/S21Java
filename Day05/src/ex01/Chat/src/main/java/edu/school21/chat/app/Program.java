package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/javaDay05";
    private static final String login = "maksimzuravlev";
    private static final String password = "136ey49v27723";

    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(login);
        config.setPassword(password);

        DataSource dataSource = new HikariDataSource(config);
        MessagesRepositoryJdbcImpl getMessage = new MessagesRepositoryJdbcImpl(dataSource);

        System.out.println("Введите ID сообщения:");
        Scanner scanner = new Scanner(System.in);
        Optional<Message> message = getMessage.findById(scanner.nextLong());
//        System.out.println("Message:");
        System.out.println(message.get());
    }
}
