package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import java.util.Objects;

public class UsersServiceImpl {
    private final UsersRepository ur;

    public UsersServiceImpl(UsersRepository ur) {
        this.ur = ur;
    }

    boolean authenticate(String login, String password) {
        User user = ur.findByLogin(login);
        if (user.isAuthentication()) {
            throw new AlreadyAuthenticatedException("This user is authentication");
        }
        if (Objects.equals(user.getPassword(), password)) {
            user.setAuthentication(true);
            ur.update(user);
            return true;
        } else {
            return false;
        }
    }
}
