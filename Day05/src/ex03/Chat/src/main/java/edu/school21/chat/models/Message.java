package edu.school21.chat.models;

import lombok.*;

import java.sql.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Message {
    private long id;
    private User messageAuthor;
    private Chatroom messageRoom;
    private String message;
    private Timestamp dateTimeMessage;

    @Override
    public String toString() {
        return "Message:\n" +
                "  id=" + id + "\n" +
                "  messageAuthor=" + messageAuthor + "\n" +
                "  messageRoom=" + messageRoom + "\n" +
                "  message='" + message + "'\n" +
                "  dateTime=" + dateTimeMessage;
    }

}


