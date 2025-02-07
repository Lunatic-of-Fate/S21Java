package ex05;

public class User {
    private final int id;
    private final String name;
    private int money;
    private final TransactionsLinkedList userTransaction;

    public User(String name, int money) {

        if (money >= 0) {
            this.id = UserIdsGenerator.getInstance().generateId();
            this.name = name;
            this.money = money;
        } else {
            throw new IllegalArgumentException("Баланс не может быть отрицательным");
        }
        userTransaction = new TransactionsLinkedList();
    }

    public TransactionsLinkedList getUserTransaction() {
        return userTransaction;
    }

    public void addUserTransaction(Transaction userTransaction) {
        this.userTransaction.addFirst(userTransaction);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        if (money < 0) throw new IllegalArgumentException("Баланс не может быть отрицательным!");
        this.money = money;
    }

    public void updateMoney(int amount) {
        if (this.money + amount < 0) throw new IllegalArgumentException("Баланс не может быть отрицательным!");
        this.money += amount;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", money=" + money + ", userTransaction:\n -> " + userTransaction + '}';
    }
}
