package org.webapp;

class InterruptTask implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " performing task " + i);
                Thread.sleep(1000);  // Simulate task execution
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}

public class ThreadInterruptionDemo {
    public static void main(String[] args) {
        Thread workerThread = new Thread(new InterruptTask(), "WorkerThread");

        workerThread.start();  // Start the thread

        try {
            Thread.sleep(3000);  // Main thread waits for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        workerThread.interrupt();  // Interrupt the worker thread
        System.out.println("Main thread requested interruption.");
    }
}
