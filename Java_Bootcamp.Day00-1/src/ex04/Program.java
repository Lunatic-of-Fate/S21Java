package src.ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        frequencyAnalysis(input);
    }

    public static void frequencyAnalysis(String input) {
        input = input.replaceAll("\n$", ""); // Удаление символа конца строки
        final int MAX_CHAR = 65536; // Максимальное значение символа по ASCII
        int[] frequency = new int[MAX_CHAR];    // Индекс - ASCII код символа, значение элемента - кол-во повторений
        for(char c : input.toCharArray()) { // Считаем кол-во повторений символа
            frequency[c]++;
        }

        char[] topChars = new char[10];
        int[] topCounts = new int[10];

        //  Сортировка массивов
        for(int i = 0; i < MAX_CHAR; i++) {
            if(frequency[i] > 0) {
                for (int j = 0; j < topCounts.length; j++) {
                    if (topCounts[j] < frequency[i]) {
                        for (int k = topCounts.length - 1; k > j; k--) {
                            topCounts[k] = topCounts[k - 1];
                            topChars[k] = topChars[k - 1];
                        }
                        topCounts[j] = frequency[i];
                        topChars[j] = (char) i;
                        break;
                    }
                }
            }
        }

        //  Вывод гистаграммы
        for (int height = 11; height > 0; height--) {
            if(height == 11) {
                for (int topCount : topCounts) {
                    if (topCount * 10 / topCounts[0] >= height - 1) {
                        System.out.print(topCount + "  ");
                    }
                }
            } else {
                for (int topCount : topCounts) {
                    if (topCount * 10 / topCounts[0] >= height) {
                        System.out.print("#  ");
                    } else {
                        if (topCount * 10 / topCounts[0] >= height - 1 && topCount > 0) {
                            System.out.printf("%1d  ", topCount);
                        }
                    }
                }
            }
            System.out.println();
        }
        // Выводим символы внизу
        for (int i = 0; i < topCounts.length; i++) {
            if (topCounts[i] > 0) {
                System.out.print(topChars[i] + "  ");
            }
        }
        System.out.println();
    }
}
