package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signUp(String login, String password) {
        if(usersRepository.findByLogin(login).isPresent()) {
            throw new RuntimeException("This user is exist");
        } else {
            val encodedPassword = passwordEncoder.encode(password);
            usersRepository.save(new User(2L, login, encodedPassword));
        }
        return "Successful!";
    }
}
