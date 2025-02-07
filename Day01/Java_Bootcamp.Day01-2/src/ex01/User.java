package ex01;
public class User {
    private final int id;
    private final String name;
    private int money;


    public User(String name, int money) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;

        if (money >= 0) {
            this.money = money;
        } else {
            throw new IllegalArgumentException("Баланс не должен быть отрицательным");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "ex01.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

}


