package edu.school21.chat.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Chatroom {
    private long id;
    private String name;
    private User owner;
    private List<Message> messages;
}
