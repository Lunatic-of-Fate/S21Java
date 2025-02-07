package ex05;

import java.util.UUID;

public interface UsersList {

    void addUser(User newUser);
    User getUserByName(String name);
    User getUserByIndex(int index);
    User getUserById(int id);
    int getCountUsers();
}
