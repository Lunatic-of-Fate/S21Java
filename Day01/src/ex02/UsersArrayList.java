package ex02;

import java.util.Arrays;

public class UsersArrayList implements UsersList {
    private User[] Users;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public UsersArrayList() {
        Users = new User[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void addUser(User newUser) {
        ensureCapacity();
        Users[size++] = newUser;
    }

    @Override
    public User getUserByIndex(int index) {
        checkIndex(index);
        return Users[index];
    }

    @Override
    public User getUserById(int id) {
        return Users[checkId(id)];
    }

    @Override
    public int getCountUsers() {
        return size;
    }

    private void ensureCapacity() {
        if (size == Users.length) {
            int newCapacity = Users.length * 2;
            Users = Arrays.copyOf(Users, newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Индекс: " + index + " Размер: " + size);
        }
    }

    private int checkId(int id) {
        for (int index = 0; index < size; index++) {
            if (id == Users[index].getId()) {
                return index;
            }
        }
        throw new UserNotFoundException("Пользователя с таким Id не существует!");
    }

}
