package edu.school21.sockets.models;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class User {
    private long id;
    private String login;
    private String password;
}
