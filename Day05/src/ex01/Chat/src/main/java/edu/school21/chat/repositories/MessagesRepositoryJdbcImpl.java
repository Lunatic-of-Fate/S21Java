package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Message message = new Message();

        String query = "SELECT messages.id as messageId,\n" +
                "\tmessages.message,\n" +
                "\tmessages.date,\n" +
                "\tusers.id as userId,\n" +
                "\tusers.login as userLogin,\n" +
                "\tusers.password,\n" +
                "\tchatrooms.id as chatroomId,\n" +
                "\tchatrooms.name as chatroomName\n" +
                "\tFROM messages \n" +
                "\tJOIN public.users on messages.userid = users.id \n" +
                "\tJOIN public.chatrooms on messages.chatroomid = chatrooms.id \n" +
                "\tWHERE public.messages.id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            message.setId(id);
            message.setMessageAuthor(
                    new User(resultSet.getLong("userId"),
                            resultSet.getString("userLogin"),
                            resultSet.getString("password"),
                            null,
                            null));
            message.setMessageRoom(
                    new Chatroom(resultSet.getLong("chatroomId"),
                            resultSet.getString("chatroomName"),
                            null,
                            null));
            message.setMessage(resultSet.getString("message"));
            message.setDateTimeMessage(resultSet.getTimestamp("date"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(message);
    }
}
