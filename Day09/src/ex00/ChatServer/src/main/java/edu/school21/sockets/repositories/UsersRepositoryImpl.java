package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@Repository
public class UsersRepositoryImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?",
                        new Object[]{id},
                        new BeanPropertyRowMapper(User.class))
                .stream().
                findFirst();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        this.jdbcTemplate.update("INSERT INTO users(login, password) VALUES (?, ?)",
                entity.getLogin(), entity.getPassword());
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update("UPDATE users SET login = ? WHERE id = ?", user.getLogin(), user.getId());
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return jdbcTemplate.query("SELECT * FROM users WHERE login = ?",
                        new Object[]{login},
                        new BeanPropertyRowMapper(User.class))
                .stream()
                .findFirst();
    }

    @Override
    public void createTable() {
        jdbcTemplate.update("CREATE TABLE users (id SERIAL PRIMARY KEY, login VARCHAR(50), password VARCHAR(500));");
    }

    @Override
    public void deleteTable() {
        jdbcTemplate.update("DROP TABLE users");
    }
}
