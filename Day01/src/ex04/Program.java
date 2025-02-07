package ex04;
import java.util.Arrays;
public class Program {
    public static void main(String[] args) {
        User max = new User("Max", 90000000);
        User dasha = new User("Dasha", 90000000);

        TransactionsService school = new TransactionsService();
        school.addUser(max);
        school.addUser(dasha);
        school.transfer(school.getUsersList().getUserByName("Max").getId(),
                school.getUsersList().getUserByName("Dasha").getId(),
                -6000);


        Transaction fromMaxToDasha = new Transaction(max, dasha, PaymentCategory.DEBIT, -1000);
        Transaction fromDashaToMax = new Transaction(dasha, max, PaymentCategory.CREDIT, 1000);
        Transaction fromDashaToMax2 = new Transaction(dasha, max, PaymentCategory.DEBIT, -10000);
        Transaction fromDashaToMax3 = new Transaction(dasha, max, PaymentCategory.DEBIT, -10000);
        school.removeTransaction(max.getUserTransaction().getHead().data.getId(), max.getId());
//        Arrays.stream(school.getUserTransactions(school.getUsersList().getUserByName("Max").getId())).forEach(System.out::println);
        Arrays.stream(school.validateTransactions(dasha)).forEach(System.out::println);
//        System.out.println(school.getUsersList().getUserByName("Max"));
//        System.out.println(school.getUsersList().getUserByName("Dasha"));

    }
}
