package org.webapp;

import java.util.*;
import java.util.concurrent.*;

public class CollectionPerformanceBenchmark {
    private static final int THREAD_COUNT = 5;
    private static final int OPERATIONS = 10000;

    public static void main(String[] args) throws InterruptedException {
        benchmarkMap(new ConcurrentHashMap<>(), "ConcurrentHashMap");
        benchmarkMap(Collections.synchronizedMap(new HashMap<>()), "Synchronized HashMap");

        benchmarkList(new CopyOnWriteArrayList<>(), "CopyOnWriteArrayList");
        benchmarkList(Collections.synchronizedList(new ArrayList<>()), "Synchronized ArrayList");
    }

    private static void benchmarkMap(Map<String, Integer> map, String name) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        long startTime = System.nanoTime();
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    String key = "key" + (j % 100);
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.nanoTime();

        System.out.printf("%s: %d ms%n", name, (endTime - startTime) / 1_000_000);
    }

    private static void benchmarkList(List<String> list, String name) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        long startTime = System.nanoTime();
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    list.add("Element " + j);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.nanoTime();

        System.out.printf("%s: %d ms%n", name, (endTime - startTime) / 1_000_000);
    }
}
