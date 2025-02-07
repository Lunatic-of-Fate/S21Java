package ex03;


import java.util.Arrays;

public class Program {

    public static void main(String[] args) {
        User max = new User("Max", 90000000);
        User dasha = new User("Dasha", 90000000);


        Transaction fromMaxToDasha = new Transaction(max, dasha, PaymentCategory.DEBIT, 1000);
        Transaction fromDashaToMax = new Transaction(dasha, max, PaymentCategory.CREDIT, 1000);
        Transaction fromDashaToMax2 = new Transaction(dasha, max, PaymentCategory.DEBIT, 10000);
        Transaction fromDashaToMax3 = new Transaction(dasha, max, PaymentCategory.DEBIT, 10000);



        System.out.println(max);
        System.out.println(dasha);
    }
}
