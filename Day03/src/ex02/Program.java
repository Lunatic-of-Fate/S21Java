package ex02;

import java.util.Random;

public class Program {
    public static void setTotalSum(int totalSum) {
        Program.totalSum += totalSum;
    }
    private static int totalSum;

    public static void main(String[] args) throws InterruptedException {

        if (args[0] != null && args[1] != null) {
            String[] input = args[0].split("=");
            String[] input2 = args[1].split("=");
            if (input[0].equals("--arraySize") && input[1] != null) {
                if (input2[0].equals("--threadsCount") && input2[1] != null) {
                    int arrLenght = Integer.parseInt(input[1]);
                    int threadCount = Integer.parseInt(input2[1]);
                    int[] inputArr = new int[arrLenght];
                    Random random = new Random();
                    for (int i = 0; i < inputArr.length; i++) {
                        inputArr[i] = random.nextInt();
                    }

                    int arrLenghtOstatok = inputArr.length % threadCount;
                    arrLenght = (inputArr.length / threadCount);
                    if (arrLenghtOstatok != 0) arrLenght++;
                    int index = 0;
                    int[][] arr = new int[threadCount][arrLenght];
                    Thread[] myThread = new Thread[threadCount];
                    for (int i = 0; i < threadCount; i++) {
                        for (int j = 0; j < arrLenght; j++) {
                            if (index >= inputArr.length) {
                                arr[i][j] = 0;
                            } else {
                                arr[i][j] = inputArr[index];
                            }
                            index++;
                        }
                        myThread[i] = new Thread(new MyThread(arr[i]));
                        myThread[i].start();
                    }
                    for (int i = 0; i < threadCount; i++) {
                        myThread[i].join();
                    }
                    System.out.println(totalSum);
                }
            }
        }


    }


}
