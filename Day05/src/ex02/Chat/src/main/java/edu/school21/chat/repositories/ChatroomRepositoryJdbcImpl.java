package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    private final DataSource dataSource;
    public ChatroomRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Chatroom> findById(long id) {
        Chatroom chatroom = new Chatroom();

        String query = "SELECT * FROM chatrooms WHERE chatrooms.id = ?";
        try(PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                throw new NotSavedSubEntityException("Chatroom c таким ID не существует");
            }
            chatroom.setId(resultSet.getLong("id"));
            chatroom.setMessages(null);
            chatroom.setName("name");
            chatroom.setOwner(null);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return Optional.of(chatroom);
    }
}
