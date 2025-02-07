package ex00;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
    private int money;


    public User(String name, int money) {
        id = UUID.randomUUID();
//        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;

        if (money >= 0) {
            this.money = money;
        } else {
            throw new IllegalArgumentException("Баланс не должен быть отрицательным");
        }
    }

    public static void applyTransaction(Transaction transaction) {
        transaction.getPaymentReceiver().money -= transaction.getSumPayment();
        transaction.getPaymentSender().money += transaction.getSumPayment();
    }

    public UUID getId() {
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
        return "ex00.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
