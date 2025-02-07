package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import lombok.ToString;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = new User();

        String query = "SELECT * FROM users\n" +
                "\tWHERE users.id = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException("User c таким ID не существует");
            }
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> users = new ArrayList<>();
        String query = """
                             
                                WITH unique_users AS (
                                     SELECT DISTINCT users.id AS userId, login, password
                                     FROM users
                                     ORDER BY users.id
                                     LIMIT ? OFFSET ?
                                 ),
                                      page_users AS (
                                          SELECT
                                              u.userId,
                                              u.login,
                                              u.password,
                                              cc.id AS createdChatroomsId,
                                              cc.name AS createdChatroomName,
                                              c.id AS participatingChatroomsId,
                                              c.name AS participatingChatroomsName,
                                              c.owneruserid AS participatingChatroomsOwner
                                          FROM unique_users u
                                                   LEFT JOIN chatrooms cc ON u.userId = cc.owneruserid
                                                   LEFT JOIN messages m ON u.userId = m.userid
                                                   LEFT JOIN chatrooms c ON c.id = m.chatroomid
                                      )
                                 SELECT * FROM page_users
                                 ORDER BY userId, createdChatroomsId, participatingChatroomsId;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, size * page);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (users.isEmpty() || users.getLast().getId() != resultSet.getLong("userId")) {
                    User user = new User(resultSet.getLong("userId"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            new ArrayList<>(),
                            new ArrayList<>());
                    users.add(user);
                }
                if (users.getLast().getListCreateChatroom().isEmpty() ||
                        users.getLast().getListCreateChatroom().getLast().getId() !=
                                resultSet.getLong("createdChatroomsId")) {
                    users.getLast().getListCreateChatroom().add(new Chatroom
                            (resultSet.getLong("createdChatroomsId"),
                                    resultSet.getString("createdChatroomName"),
                                    null, null));
                }
                if (users.getLast().getListMessageChatroom().isEmpty() ||
                        users.getLast().getListMessageChatroom().getLast().getId() <
                                resultSet.getLong("participatingChatroomsId")) {
                    users.getLast().getListMessageChatroom().add(new Chatroom
                            (resultSet.getLong("participatingChatroomsId"),
                                    resultSet.getString("participatingChatroomsName"),
                                    null, null));
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return users;
    }
}
