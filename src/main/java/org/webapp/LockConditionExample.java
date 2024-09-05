package org.webapp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class SharedResource {
    private int value = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            value++;
            System.out.println("Produced: " + value);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
            System.out.println("Consumed: " + value);
        } finally {
            lock.unlock();
        }
    }
}

public class LockConditionExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            try {
                resource.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                resource.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        consumer.start();
        producer.start();
    }
}
