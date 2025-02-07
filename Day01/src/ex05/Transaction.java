package ex05;

import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final User paymentSender;
    private final User paymentRecipient;
    private final PaymentCategory paymentCategory;
    private final int sumPayment;


    public Transaction(User paymentSender, User paymentRecipient, PaymentCategory paymentCategory, int sumPayment) {

        if ((paymentCategory.equals(PaymentCategory.DEBIT) && sumPayment > 0) || (paymentCategory.equals(PaymentCategory.CREDIT) && sumPayment < 0)) {
            throw new IllegalArgumentException("Категория платежа не совпадает с суммой");
        }

        if ((paymentCategory == PaymentCategory.CREDIT && paymentRecipient.getMoney() < sumPayment) || (paymentCategory == PaymentCategory.DEBIT && paymentSender.getMoney() < sumPayment)) {
            throw new IllegalArgumentException("Деняк нет");
        }

        id = UUID.randomUUID();
        this.paymentSender = paymentSender;
        this.paymentRecipient = paymentRecipient;
        this.paymentCategory = paymentCategory;
        this.sumPayment = sumPayment;
        applyTransaction();
    }

    private Transaction(Transaction reverseTransaction) {
        this.id = reverseTransaction.id;
        this.paymentSender = reverseTransaction.paymentRecipient;
        this.paymentRecipient = reverseTransaction.paymentSender;
        this.paymentCategory = (reverseTransaction.paymentCategory.equals(PaymentCategory.CREDIT) ? PaymentCategory.DEBIT : PaymentCategory.CREDIT);
        this.sumPayment = -reverseTransaction.sumPayment;

        paymentSender.updateMoney(sumPayment);
        paymentSender.addUserTransaction(this);
    }

    private void applyTransaction() {
        if (paymentCategory == PaymentCategory.DEBIT) {
            this.paymentSender.updateMoney(sumPayment); // Уменьшение баланса отправителя
            this.paymentSender.addUserTransaction(this);
            new Transaction(this);
        } else if (paymentCategory == PaymentCategory.CREDIT) {
            this.paymentSender.updateMoney(sumPayment); // Увеличение баланса отправителя
            this.paymentSender.addUserTransaction(this);
            new Transaction(this);
        }
    }

    public UUID getId() {
        return id;
    }

    public User getPaymentRecipient() {
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


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", paymentSender=" + paymentSender.getName() +
                ", paymentReceiver=" + paymentRecipient.getName() +
                ", paymentCategory=" + paymentCategory +
                ", sumPayment=" + sumPayment +
                '}';
    }
}

