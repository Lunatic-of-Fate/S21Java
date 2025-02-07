package ex02;

public class MyThread implements Runnable {
    private final int[] arr;

    private int result = 0;

    public MyThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int a : arr) {
            result += a;
        }
        Program.setTotalSum(result);
    }
}
