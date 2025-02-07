package ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class MyThread implements Runnable {

    private String saveDir = "src/ex03/download/";
    private int numberThread;

    public MyThread(int numberThread) {
        this.numberThread = numberThread + 1;

    }

    @Override
    public void run() {
        while (!Program.getQueue().isEmpty()) {
            URI uri;
            int numberFiles;
            synchronized (Program.lock) {
                numberFiles = Program.numberFiles;
                System.out.println("Thread-" + numberThread + " start download file number " + numberFiles);
                uri = Program.getQueue().poll();
                Program.numberFiles++;
            }
            if (uri != null) {
                if(downloadFile(uri, saveDir)) {
                    System.out.println("Thread-" + numberThread + " finish download file number " + numberFiles);
                } else {
                    System.out.println("Thread-" + numberThread + " error download file number " + numberFiles);
                };
            }
        }

    }

    private boolean downloadFile(URI uri, String saveDir) {

        try {
            HttpURLConnection httpConn = (HttpURLConnection) uri.toURL().openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String fileName = uri.getPath().substring(uri.getPath().lastIndexOf('/') + 1);

                // Открываем InputStream для чтения данных
                InputStream inputStream = new BufferedInputStream(httpConn.getInputStream());
                FileOutputStream outputStream = new FileOutputStream(saveDir + fileName);

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                return true;
            } else if (httpConn.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM ||
                    httpConn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
                String newUri = httpConn.getHeaderField("Location");
                downloadFile(new URI(newUri), saveDir);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}