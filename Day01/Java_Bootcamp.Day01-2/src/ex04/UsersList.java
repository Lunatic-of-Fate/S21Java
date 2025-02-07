package ex04;

import java.util.UUID;

public interface UsersList {

    void addUser(User newUser);
    User getUserByName(String name);
    User getUserByIndex(int index);
    User getUserById(UUID id);
    int getCountUsers();
}
