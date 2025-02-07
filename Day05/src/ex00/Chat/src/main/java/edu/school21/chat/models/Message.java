package edu.school21.chat.models;

import java.sql.*;

public class Message {

    private int id;
    private User messageAuthor;
    private Chatroom messageRoom;
    private Timestamp dateTimeMessage;
}
