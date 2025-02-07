package ex00;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final User paymentReceiver;
    private final User paymentSender;
    private final PaymentCategory paymentCategory;
    private final int sumPayment;


    public Transaction(User paymentSender, User paymentReceiver, PaymentCategory paymentCategory, int sumPayment) {
        if ((sumPayment >= 0 && paymentCategory == PaymentCategory.CREDIT) ||
            (sumPayment < 0 && paymentCategory == PaymentCategory.DEBIT)) {
            throw new IllegalArgumentException("Сумма платежа не соответствует категории");
        }

        if ((paymentCategory == PaymentCategory.CREDIT && paymentSender.getMoney() < sumPayment) ||
            (paymentCategory == PaymentCategory.DEBIT && paymentReceiver.getMoney() < sumPayment)) {
            throw new IllegalArgumentException("Деняк нет");
        }

        id = UUID.randomUUID();
        this.paymentSender = paymentSender;
        this.paymentReceiver = paymentReceiver;
        this.paymentCategory = paymentCategory;
        this.sumPayment = sumPayment;
    }

    public UUID getId() {
        return id;
    }

    public User getPaymentReceiver() {
        return paymentReceiver;
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
                ", paymentReceiver=" + paymentReceiver +
                ", paymentSender=" + paymentSender +
                ", paymentCategory=" + paymentCategory +
                ", sumPayment=" + sumPayment +
                '}';
    }
}

