package ex00;
public class Program {
    private static int count = 0;

    public static void main (String[] args) {
        if(args[0] != null) {
            String[] input = args[0].split("=");
            if (input[0].equals("--count") && input[1] != null) {


                count = Integer.parseInt(input[1]);
                Thread eggThread = new Thread(new EggRunnable());
                Thread henThread = new Thread(new HenRunnable());
                eggThread.start();
                henThread.start();

            try {
                    eggThread.join();
                    henThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i = count; i != 0; i--) {
                    System.out.println("Human");
                }
            }
        }
        }

    static class EggRunnable implements Runnable {
        @Override
        public void run() {
            for(int i = count; i > 0; i--) {
                System.out.println("Egg");
            }
        }
    }
    static class HenRunnable implements Runnable {
        @Override
        public void run() {
            for(int i = count; i > 0; i--) {
                System.out.println("Hen");
            }
        }
    }
}



