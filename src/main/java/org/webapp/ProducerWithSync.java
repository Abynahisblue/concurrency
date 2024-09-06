package org.webapp;

public class ProducerWithSync implements Runnable {
    private final SharedBuffer buffer;

    public ProducerWithSync(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;
        while (value < 10000) {
            try {
                buffer.produce(value++);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}