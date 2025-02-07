package ex03;

public class MyThreads {


    public static void createThread(int threadCount) {
        Thread[] myThread = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            myThread[i] = new Thread(new MyThread(i));
            myThread[i].start();
        }
    }
}



