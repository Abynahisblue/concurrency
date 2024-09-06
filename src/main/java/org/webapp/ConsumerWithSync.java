package org.webapp;

public class ConsumerWithSync implements Runnable {
    private final SharedBuffer buffer;

    public ConsumerWithSync(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                buffer.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
