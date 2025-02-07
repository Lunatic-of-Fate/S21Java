package ex00;

public class Program {

    public static void main(String[] args) {
        User max = new User("Max", 10000000);
        User dasha = new User("Dasha", 0);

        System.out.println(max);
        System.out.println(dasha);

        Transaction fromMaxToDasha = new Transaction(max, dasha, PaymentCategory.CREDIT, -1000000);
        User.applyTransaction(fromMaxToDasha);
        Transaction fromDashaToMax = new Transaction(dasha, max, PaymentCategory.DEBIT, 1000000);
        User.applyTransaction(fromDashaToMax);

        System.out.println(max);
        System.out.println(dasha);
    }
}
