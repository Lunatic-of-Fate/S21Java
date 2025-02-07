package edu.school21.chat.models;

import lombok.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chatroom> listCreateChatroom;
    private List<Chatroom> listMessageChatroom;
}
