package org.webapp;

class BankAccount {
    private int balance = 100;

    // Synchronized method to ensure thread safety
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + ": Insufficient balance.");
        }
    }
}

public class SynchronizedExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        // Create threads to access the same account
        Thread t1 = new Thread(() -> account.withdraw(70), "Thread 1");
        Thread t2 = new Thread(() -> account.withdraw(50), "Thread 2");

        t1.start();
        t2.start();
    }
}
