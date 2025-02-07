package edu.school21.spring.service.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("edu.school21.spring.service")
@PropertySource("classpath:db.properties")
public class ApplicationConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String name;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.name}")
    private String driver;


    @Bean
    public DataSource hikariDataSource() {
        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSource driverManagerDataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }
}
