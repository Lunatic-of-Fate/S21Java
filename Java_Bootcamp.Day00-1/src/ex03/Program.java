package src.ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int scan_mark;
        int mark_min = 9;
        int week = 1;
        StringBuilder sb = new StringBuilder();
        String str = scanner.next();
        if (!str.equals("Week")) {
            scanner.close();
            System.err.print("Illegal Argument");
            System.exit(-1);
        }
        while (!str.equals("42")) {
            if (week == scanner.nextInt()) {
                for (int i = 0; i < 5; i++) {
                    scan_mark = scanner.nextInt();
                    if (scan_mark < mark_min) {
                        mark_min = scan_mark;
                    }
                }
                sb.append("Week ");
                sb.append(week);
                sb.append(" ");
                sb.append("=".repeat(mark_min));
                sb.append(">\n");
                str = scanner.next();

                mark_min = 9;
                week++;
            } else {
                scanner.close();
                System.err.print("Illegal Argument");
                System.exit(-1);
            }
        }
        scanner.close();
        String result = sb.toString();
        System.out.println(result);
    }
}
