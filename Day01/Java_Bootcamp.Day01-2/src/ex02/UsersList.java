package ex02;

public interface UsersList {

    void addUser(User newUser);
    User getUserByIndex(int index);
    User getUserById(int id);
    int getCountUsers();
}
