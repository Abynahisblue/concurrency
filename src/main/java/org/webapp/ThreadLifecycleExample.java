package org.webapp;

class ThreadLifecycle extends Thread {
    @Override
    public void run() {
        System.out.println(getName() + " is in the RUNNABLE state.");
        try {
            Thread.sleep(1000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " is in the TERMINATED state.");
    }
}

public class ThreadLifecycleExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadLifecycle thread = new ThreadLifecycle();
        System.out.println(thread.getName() + " is in the NEW state.");
        thread.start(); // Thread moves to the RUNNABLE state
        System.out.println(thread.getName() + " is in the RUNNABLE state.");

        thread.join(); // Wait for the thread to finish
        System.out.println(thread.getName() + " is in the TERMINATED state.");
    }
}
