package ex03;

import java.net.URI;
import java.util.LinkedList;
import java.util.Queue;


public class Program {
    private static final Queue<URI> queue = new LinkedList<>();
    public static Object lock = new Object();
    public static int numberFiles = 1;

    public static Queue<URI> getQueue() {
        return queue;
    }

    public static void main(String[] args) {
        if (args[0] != null) {
            String[] input = args[0].split("=");
            if(Integer.parseInt(input[1]) > 0 && Integer.parseInt(input[1]) < 2000000) {
                int threadCount = Integer.parseInt(input[1]);
                ReadFiles.readFiles(queue);
                MyThreads.createThread(threadCount);
            }
        }

    }
}