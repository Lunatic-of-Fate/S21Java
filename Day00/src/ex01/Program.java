package src.ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        scanner.close();
        int count = 1;
        if(a > 1) {
            for(int i = 2; i <= Math.sqrt(a); i++) {
                if(a % i == 0) {
                    System.out.println("false " + count);
                    break;
                }
                if(i == (int)Math.sqrt(a)) {
                    System.out.println("true " + count);
                }
                count ++;
            }
        } else {
            System.err.print("Illegal Argument");
            System.exit(-1);
        }
    }
}