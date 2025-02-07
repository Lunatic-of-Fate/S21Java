package ex05;


import java.util.UUID;

public class TransactionsService {

    private final UsersArrayList usersList;

    public TransactionsService() {
        usersList = new UsersArrayList();
    }

    void addUser(User newUser) {
        usersList.addUser(newUser);
    }

    public UsersArrayList getUsersList() {
        return usersList;
    }

    int getUserBalance(int userId) {
        return usersList.getUserById(userId).getMoney();
    }

    void transfer(int senderId, int receiverId, int amount) {
        Transaction autoTransaction = new Transaction(usersList.getUserById(senderId), usersList.getUserById(receiverId), ((amount > 0) ? PaymentCategory.CREDIT : PaymentCategory.DEBIT), amount);
    }

    Transaction[] getUserTransactions(int userId) {
        return usersList.getUserById(userId).getUserTransaction().toArray();
    }

    void removeTransaction(UUID transactionId, int userId) {
        usersList.getUserById(userId).getUserTransaction().remove(transactionId);
    }

    Transaction[] validateTransactions(User user) {
        TransactionsLinkedList noValidateTransactions = new TransactionsLinkedList();

        Node current = user.getUserTransaction().getHead();
        UUID currentTransactionId;

        while (current != null) {
            currentTransactionId = current.data.getId();
            Node currentRecipient = current.data.getPaymentRecipient().getUserTransaction().getHead();
            boolean isContain = false;
            while (currentRecipient != null) {
                if (currentTransactionId == currentRecipient.data.getId()) {
                    isContain = true;
                    break;
                }
                currentRecipient = currentRecipient.next;
            }
            if (!isContain) noValidateTransactions.addFirst(current.data);
            current = current.next;
        }
        return noValidateTransactions.toArray();
    }
}
