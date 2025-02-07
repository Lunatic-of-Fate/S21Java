package edu.school21.spring.service.services;

import edu.school21.spring.service.config.TestApplicationConfig;
import edu.school21.spring.service.repositories.UsersRepository;
import edu.school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Component
public class UsersServiceImplTest {
    AnnotationConfigApplicationContext context;
    UsersRepository usersRepository;
    UsersService usersService;

    @Test
    void signUpTest_userNotSigned_success() {
        this.context =
                new AnnotationConfigApplicationContext(TestApplicationConfig.class);

        this.usersRepository = context.getBean(
                "usersRepositoryJdbc", UsersRepository.class);

        this.usersService = context.getBean(
                "usersServiceImpl", UsersServiceImpl.class);

        String result = this.usersService.signUp("test@example.com");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}