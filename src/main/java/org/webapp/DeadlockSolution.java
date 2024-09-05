package org.webapp;

class Resources {
    public synchronized void useResource(Resources other) {
        System.out.println(Thread.currentThread().getName() + " is using resource.");
        other.release();
    }

    public synchronized void release() {
        System.out.println(Thread.currentThread().getName() + " is releasing resource.");
    }
}

public class DeadlockSolution {
    public static void main(String[] args) {
        Resources resource1 = new Resources();
        Resources resource2 = new Resources();

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                synchronized (resource2) {
                    resource1.useResource(resource2);
                }
            }
        }, "Thread 1");

        Thread t2 = new Thread(() -> {
            synchronized (resource1) {  // Acquiring locks in the same order
                synchronized (resource2) {
                    resource2.useResource(resource1);
                }
            }
        }, "Thread 2");

        t1.start();
        t2.start();
    }
}
