package ex01;

public class EggRunnable implements Runnable {
    private final Object lock;
    private int count = 0;

    public EggRunnable(int count, Object lock, Boolean isEggTurn) {
        this.count = count;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (lock) {
                try {
                    System.out.println("egg");
                    lock.notifyAll();
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

