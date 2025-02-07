package edu.school21.chat.models;

import java.util.List;
import java.sql.*;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> listCreateChatroom;
    private List<Chatroom> listMessageChatroom;
}