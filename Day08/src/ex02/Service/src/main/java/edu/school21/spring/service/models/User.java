package edu.school21.spring.service.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    private long id;
    private String email;
    private String password;
}
