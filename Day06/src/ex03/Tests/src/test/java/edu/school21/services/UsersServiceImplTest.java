package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class UsersServiceImplTest {
    @Mock
    UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
    UsersServiceImpl usersService = new UsersServiceImpl(usersRepository);

    @Test
    void UsersServiceImpl_True() {
        User user = new User(1, "login", "password", false);
        given(usersRepository.findByLogin(user.getLogin())).willReturn(user);
        assertTrue(usersService.authenticate(user.getLogin(), user.getPassword()));
    }

    @Test
    void UsersServiceImpl_False() {
        User user = new User(1, "login", "password", false);
        given(usersRepository.findByLogin(user.getLogin())).willReturn(user);
        assertFalse(usersService.authenticate(user.getLogin(), "12312"));
    }

    @Test
    void UsersServiceImpl_Exception() {
        User user = new User(1, "login", "password", true);
        given(usersRepository.findByLogin(user.getLogin())).willReturn(user);
        assertThrows(AlreadyAuthenticatedException.class, () -> usersService.authenticate(user.getLogin(), "12312"));
    }
}
