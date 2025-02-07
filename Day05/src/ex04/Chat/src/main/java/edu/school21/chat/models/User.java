package edu.school21.chat.models;

import lombok.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chatroom> listCreateChatroom;
    private List<Chatroom> listMessageChatroom;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("User ID: ").append(id).append("\n");
        sb.append("Login: ").append(login).append("\n");

        // Вывод списка созданных чатрумов
        sb.append("Created Chatrooms:\n");
        for (Chatroom chatroom : listCreateChatroom) {
            sb.append("\t");
            sb.append(chatroom.toString()).append("\n"); // Предполагается, что у Chatroom есть свой toString()
        }

        // Вывод списка чатрумов сообщений
        sb.append("Message Chatrooms:\n");
        for (Chatroom chatroom : listMessageChatroom) {
            sb.append("\t");
            sb.append(chatroom.toString()).append("\n"); // Предполагается, что у Chatroom есть свой toString()
        }

        return sb.toString();
    }
}

