package ex01;

public class HenRunnable implements Runnable {
    private final Object lock;
    private int count = 0;

    public HenRunnable(int count, Object lock, Boolean isEggTurn) {
        this.count = count;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (lock) {
                try {
                    System.out.println("hen");
                    lock.notifyAll();
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
