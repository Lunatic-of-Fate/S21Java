package edu.school21.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {
    private long id;
    private String login;
    private String password;
    private boolean Authentication;
}
