package edu.school21.spring.service.services;

import edu.school21.spring.service.models.User;
import edu.school21.spring.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    public UsersServiceImpl(@Qualifier("usersRepositoryJdbc") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        User user = new User(usersRepository.findAll().getLast().getId() + 1, email, genPassword());
        usersRepository.save(user);
        return user.getPassword();
    }

    private String genPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+<>?";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            password.append(characters.charAt(new SecureRandom().nextInt(characters.length())));
        }
        return password.toString();
    }
}
