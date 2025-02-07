package ex05;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;


public class Menu {
    private final TransactionsService transactionsService;
    boolean isDev;

    public Menu(TransactionsService transactionsService, boolean isDev) {
        this.transactionsService = transactionsService;
        this.isDev = isDev;
        performTransactionService(this);
    }

    private void displayMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (isDev) {
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
        }
        System.out.println("7. Finish execution");
    }

    ;

//    public String[] scannerMenu() {

    //    }
    private void performTransactionService(Menu menu) {
        Scanner scanner = new Scanner(System.in);
        char command = '0';
        while (command != '7') {
            menu.displayMenu();
            String input = scanner.next();
            command = input.toCharArray()[0];

            if ("1234567".indexOf(command) != -1 && input.length() == 1) {
                switch (command) {
                    case '1': // Add a user
                        try {
                            System.out.println("Enter a user name and a balance");
                            String name = scanner.next();
                            int balance = scanner.nextInt();
                            User newUser = new User(name, balance);
                            transactionsService.addUser(newUser);
                            System.out.println("ex001.User with id = " + newUser.getId() + " is added");
                        } catch (Exception e) {
                            System.out.println("next try");
                            continue;
                        }
                        break;
                    case '2': // View user balances
                        try {
                            System.out.println("Enter a user ID");
                            int searchUserId = scanner.nextInt();
                            System.out.println(transactionsService.getUsersList().getUserById(searchUserId).getName() + " - " + transactionsService.getUserBalance(searchUserId));
                        } catch (Exception e) {
                            System.out.println("next try");
                            continue;
                        }
                        break;
                    case '3': // Perform a transfer
                        try {
                            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                            transactionsService.transfer(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                            System.out.println("The transfer is completed");
                        } catch (Exception e) {
                            System.out.println("next try");
                            continue;
                        }
                        break;
                    case '4': // View all transactions for a specific user
                        try {
                            System.out.println("Enter a user ID");
                            Node current = transactionsService.getUsersList().getUserById(scanner.nextInt()).getUserTransaction().getHead();
                            while (current != null) {
                                System.out.println("To " + current.data.getPaymentSender().getName() + "(id = " +
                                        current.data.getPaymentSender().getId() + ") " + current.data.getSumPayment() +
                                        " with id = " + current.data.getId());

                                current = current.next;
                            }
                        } catch (Exception e) {
                            System.out.println("next try");
                            continue;
                        }
                        break;
                    case '5': // DEV - remove a transfer by ID
                        try {
                            if (menu.isDev) {
                                System.out.println("Enter a user ID and a transfer ID");
                                int userId = scanner.nextInt();
                                UUID transferId = UUID.fromString(scanner.next());
                                int sumRemovedTransaction = transactionsService.getUsersList().getUserById(userId).getUserTransaction().getTransaction(transferId).getSumPayment();
                                transactionsService.removeTransaction(transferId, userId);
                                System.out.println("Transfer To " +
                                        transactionsService.getUsersList().getUserById(userId).getName() +
                                        "(id = " + userId + ") " + sumRemovedTransaction + " removed");
                            }
                        } catch (Exception e) {
                            System.out.println("next try");
                            continue;
                        }
                        break;
                    case '6': // DEV - check transfer validity
                        try {
                            if (menu.isDev) {
                                TransactionsLinkedList noValidateTransactions = new TransactionsLinkedList();
                                for (int i = 1; i <= transactionsService.getUsersList().getSize(); i++) {
                                    Node current = transactionsService.getUsersList().getUserById(i).getUserTransaction().getHead();
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
                                }
                                System.out.println("Check results:");
                                Node current = noValidateTransactions.getHead();
                                while (current != null) {
                                    System.out.println(
                                            current.data.getPaymentSender().getName() +
                                                    "(id = " +
                                                    current.data.getPaymentSender().getId() + ") " +
                                                    "has an unacknowledged transfer id = " +
                                                    current.data.getId() + " from " +
                                                    current.data.getPaymentRecipient().getName() + "(id = " +
                                                    current.data.getPaymentRecipient().getId() + ") for " +
                                                    current.data.getSumPayment()
                                    );

                                    current = current.next;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("next try");
                            continue;
                        }
                        break;
                }
            } else System.out.println("Again");
        }
        scanner.close();
    }
}


//1 max 10000 1 arina 10000 3 2 1 -100 4 1