package org.webapp;

import java.util.concurrent.BlockingQueue;

public class ProducerWithBlockingQueue implements Runnable {
    private final BlockingQueue<Integer> queue;

    public ProducerWithBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int value = 0;
        while (value < 10000) {
            try {
                queue.put(value++); // Blocks if the queue is full
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
