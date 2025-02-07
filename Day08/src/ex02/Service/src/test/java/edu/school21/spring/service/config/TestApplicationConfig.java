package edu.school21.spring.service.config;

import edu.school21.spring.service.repositories.UsersRepository;
import edu.school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import edu.school21.spring.service.services.UsersService;
import edu.school21.spring.service.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("edu.school21.spring.service.services")
public class TestApplicationConfig {
    @Bean
    public EmbeddedDatabase EmbeddedDatabaseDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Bean
    public UsersRepository usersRepositoryJdbc() {
        return new UsersRepositoryJdbcImpl(EmbeddedDatabaseDataSource());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepositoryJdbc());
    }
}