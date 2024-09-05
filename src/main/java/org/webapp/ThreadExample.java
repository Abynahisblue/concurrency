package org.webapp;

class MyThread extends Thread {
    private String taskName;

    public MyThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(getName() + " is executing " + taskName);
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Task 1");
        MyThread thread2 = new MyThread("Task 2");

        thread1.start(); // Start the thread
        thread2.start();
    }
}
