package org.webapp;

import java.util.concurrent.BlockingQueue;

public class ConsumerWithBlockingQueue implements Runnable {
    private final BlockingQueue<Integer> queue;

    public ConsumerWithBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.take(); // Blocks if the queue is empty
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

