package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        MessagesRepositoryJdbcImpl saveMessage = new MessagesRepositoryJdbcImpl(dataSource);
        User creator = new User(2, "user", "user", new ArrayList<>(), new ArrayList<>());
        Chatroom chatroom = new Chatroom(100, "school21", creator, new ArrayList<>());
        Message message = new Message(21, creator, chatroom, "vse hernya1",
                Timestamp.valueOf(LocalDateTime.now()));

        saveMessage.save(message);
    }
}
