package ex01;

public class Program {


    public static void main(String[] args) {
        if (args[0] != null) {
            String[] input = args[0].split("=");
            if (input[0].equals("--count") && input[1] != null) {
                int count = Integer.parseInt(input[1]);
                Object lock = new Object();
                Boolean isEggTurn = true;

                Thread eggThread = new Thread(new EggRunnable(count, lock, isEggTurn));
                Thread henThread = new Thread(new HenRunnable(count, lock, isEggTurn));

                eggThread.start();
                henThread.start();
            }
        }


    }
}
