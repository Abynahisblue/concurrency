package org.webapp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DownloadTask implements Runnable {
    private String fileName;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is downloading " + fileName);
        try {
            Thread.sleep(2000); // Simulate time taken to download
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " completed downloading " + fileName);
    }
}

public class DownloadManager {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit download tasks to the pool
        String[] filesToDownload = { "file1.mp4", "file2.jpg", "file3.pdf", "file4.docx", "file5.png" };
        for (String file : filesToDownload) {
            DownloadTask task = new DownloadTask(file);
            executorService.submit(task);
        }

        // Shutdown the executor after the tasks are completed
        executorService.shutdown();
    }
}

