package org.webapp;

class Resource {
    public synchronized void useResource(Resource other) {
        System.out.println(Thread.currentThread().getName() + " is using resource.");
        other.release();
    }

    public synchronized void release() {
        System.out.println(Thread.currentThread().getName() + " is releasing resource.");
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked Resource 1.");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " locked Resource 2.");
                    resource1.useResource(resource2);
                }
            }
        }, "Thread 1");

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + " locked Resource 2.");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + " locked Resource 1.");
                    resource2.useResource(resource1);
                }
            }
        }, "Thread 2");

        t1.start();
        t2.start();
    }
}
