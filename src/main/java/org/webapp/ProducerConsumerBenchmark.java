package org.webapp;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ProducerConsumerBenchmark {
    public static void main(String[] args) throws InterruptedException {
        // Benchmark using Synchronized Methods
        SharedBuffer sharedBuffer = new SharedBuffer(5);
        Thread producer1 = new Thread(new ProducerWithSync(sharedBuffer));
        Thread consumer1 = new Thread(new ConsumerWithSync(sharedBuffer));

        long startSync = System.currentTimeMillis();
        producer1.start();
        consumer1.start();
        producer1.join(); // Wait for the producer to finish
        consumer1.interrupt(); // Stop the consumer after production
        long endSync = System.currentTimeMillis();
        System.out.println("Synchronized method implementation time: " + (endSync - startSync) + " ms");

        // Benchmark using BlockingQueue
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);
        Thread producer2 = new Thread(new ProducerWithBlockingQueue(blockingQueue));
        Thread consumer2 = new Thread(new ConsumerWithBlockingQueue(blockingQueue));

        long startBQ = System.currentTimeMillis();
        producer2.start();
        consumer2.start();
        producer2.join(); // Wait for the producer to finish
        consumer2.interrupt(); // Stop the consumer after production
        long endBQ = System.currentTimeMillis();
        System.out.println("BlockingQueue implementation time: " + (endBQ - startBQ) + " ms");
    }
}
