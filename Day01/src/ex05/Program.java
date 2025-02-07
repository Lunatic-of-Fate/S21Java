package ex05;


public class Program {
    public static void main(String[] args) {
        boolean isDev = false;
        for (String arg : args) {
            if (arg.startsWith("--profile=dev")) {
                isDev = true;
                break;
            }
        }
        TransactionsService transactionsService = new TransactionsService();
        Menu menu = new Menu(transactionsService, isDev);
    }
}