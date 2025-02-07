package src.ex05;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String[] week = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};
        String[] weekMonth = new String[30];
        for (int i = 0; i < weekMonth.length; i++) {
            weekMonth[i] = week[i % week.length];
        }

        Scanner scanner = new Scanner(System.in);
        String name[] = new String[10];
        int daysMonth[][] = new int[30][6];

        String inp = scanner.nextLine();

        int i = 0;
        while (!inp.equals(".")) {
            name[i++] = inp;
            inp = scanner.nextLine();
        }
        inp = scanner.nextLine();
        while (!inp.equals(".")) {
            String[] str = inp.split(" ");
            for (i = 0; i < weekMonth.length; i++) {
                if (str[1].equals(weekMonth[i])) {
                    int n = Integer.parseInt(str[0]);
                    daysMonth[i][n] = 1;
                }
            }
            inp = scanner.nextLine();
        }
        int[][][][] personVisit = new int[name.length][30][6][1];
        inp = scanner.nextLine();
        while (!inp.equals(".")) {
            String[] str = inp.split(" ");
            for (i = 0; i < name.length; i++) {
                if (str[0].equals(name[i])) {
                    if(str[3].equals("HERE")) {
                        personVisit[i][Integer.parseInt(str[2]) - 1][Integer.parseInt(str[1])][0] = 1;
                    } else if (str[3].equals("NOT_HERE")) {
                        personVisit[i][Integer.parseInt(str[2]) - 1][Integer.parseInt(str[1])][0] = -1;
                    }
                }
            }
            inp = scanner.nextLine();
        }


        System.out.printf("%10s", "");
        for (int date = 0; date < 30; date++) {
            for (int time = 0; time < 6; time++){
                if(daysMonth[date][time] == 1) {
                    System.out.printf("%d:00 %s %2d|", time, weekMonth[date], (date + 1));
                }
            }
        }


        for(int personName = 0; personName < name.length; personName++) {
            if(name[personName] != null) {
                System.out.printf("\n%-10s", name[personName]);
            }
            for (int date = 0; date < 30; date++) {
                for (int time = 0; time < 6; time++){
                    if(daysMonth[date][time] == 1) {
                        if (personVisit[personName][date][time][0] != 0) {
                            System.out.printf("%10d|", personVisit[personName][date][time][0]);
                        } else if (name[personName] != null) {
                            System.out.printf("%10s|", "");
                        }
                    }
                }
            }

        }
    }
}

//John
//        Mike
//.
//        2 MO
//4 WE
//        .
//        Mike 2 28 NOT_HERE
//John 4 9 HERE
//Mike 4 9 HERE
//        .

