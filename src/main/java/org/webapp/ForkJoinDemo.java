package org.webapp;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;
    private int[] numbers;
    private int start;
    private int end;

    public SumTask(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            int mid = start + length / 2;
            SumTask leftTask = new SumTask(numbers, start, mid);
            SumTask rightTask = new SumTask(numbers, mid, end);

            leftTask.fork();  // Execute left task in parallel
            int rightResult = rightTask.compute();  // Execute right task in current thread
            int leftResult = leftTask.join();  // Wait for left task to complete

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) {
        int[] numbers = new int[100];
        for (int i = 0; i < 100; i++) {
            numbers[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);

        int sum = pool.invoke(task);
        System.out.println("Sum: " + sum);
    }
}
