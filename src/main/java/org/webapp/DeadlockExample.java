package org.webapp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    private final Lock lock = new ReentrantLock();

    public void acquire() {
        lock.lock();
    }

    public void release() {
        lock.unlock();
    }
}

public class DeadlockExample {
    public static void main(String[] args) throws InterruptedException {
        Resource r1 = new Resource();
        Resource r2 = new Resource();

        Thread t1 = new Thread(() -> {
            r1.acquire();
            System.out.println("Thread 1 acquired Resource 1");
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            r2.acquire();
            System.out.println("Thread 1 acquired Resource 2");
            r1.release();
            r2.release();
        });

        Thread t2 = new Thread(() -> {
            r2.acquire();
            System.out.println("Thread 2 acquired Resource 2");
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            r1.acquire();
            System.out.println("Thread 2 acquired Resource 1");
            r2.release();
            r1.release();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
