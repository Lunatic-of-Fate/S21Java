package ex03;

import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final User paymentSender; // отправитель
    private final User paymentRecipient; // получатель
    private final PaymentCategory paymentCategory; //
    private final int sumPayment;


    public Transaction(User paymentSender, User paymentRecipient, PaymentCategory paymentCategory, int sumPayment) {

        if ((paymentCategory == PaymentCategory.CREDIT && paymentRecipient.getMoney() < sumPayment) ||
                (paymentCategory == PaymentCategory.DEBIT && paymentSender.getMoney() < sumPayment)) {
            throw new IllegalArgumentException("Деняк нет");
        }

        id = UUID.randomUUID();
        this.paymentSender = paymentSender;
        this.paymentRecipient = paymentRecipient;
        this.paymentCategory = paymentCategory;
        this.sumPayment = sumPayment;
        applyTransaction();

    }

    public UUID getId() {
        return id;
    }

    public User getPaymentReceiver() {
        return paymentRecipient;
    }

    public User getPaymentSender() {
        return paymentSender;
    }

    public PaymentCategory getPaymentCategory() {
        return paymentCategory;
    }

    public int getSumPayment() {
        return sumPayment;
    }

    private void applyTransaction() {
        if (paymentCategory == PaymentCategory.DEBIT) {
            paymentSender.updateMoney(-sumPayment); // Уменьшение баланса отправителя
            paymentSender.addUserTransaction(this);
            paymentRecipient.updateMoney(sumPayment); // Увеличение баланса получателя
            paymentRecipient.addUserTransaction(this);

        } else if (paymentCategory == PaymentCategory.CREDIT) {
            paymentSender.updateMoney(sumPayment); // Увеличение баланса отправителя
            paymentSender.addUserTransaction(this);
            paymentRecipient.updateMoney(-sumPayment); // Уменьшение баланса получателя
            paymentRecipient.addUserTransaction(this);
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", paymentReceiver=" + paymentRecipient.getName() +
                ", paymentSender=" + paymentSender.getName() +
                ", paymentCategory=" + paymentCategory +
                ", sumPayment=" + sumPayment +
                '}';
    }
}

