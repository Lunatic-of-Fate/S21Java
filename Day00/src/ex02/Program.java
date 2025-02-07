package src.ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int scan = 0;
        scan = scanner.nextInt();
        int count = 0;
        while(scan != 42) {
            int res = 0;
            while(scan > 0) {
                res += scan % 10;
                scan /= 10;
            }
            for(int i = 2; i < (int)Math.sqrt(res); i++) {
                if(res % i == 0) {
                    count++;
                }
            }
            scan = scanner.nextInt();
        }
        scanner.close();
        System.out.println("Count of coffee-request " + count);
    }
}
