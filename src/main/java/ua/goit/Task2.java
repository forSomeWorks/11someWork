package ua.goit;

import java.util.StringJoiner;

public class Task2 {
    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(15);
        fizzBuzz.letsDoIt();

    }
}

class FizzBuzz {
    private final int endNumber;
    private volatile int currentNumber;
    private volatile StringJoiner result = new StringJoiner(", ", "", ".");

    public FizzBuzz(int endNumber) {
        this.endNumber = endNumber;
        this.currentNumber = 1;
    }

    public void letsDoIt() {
        new Thread(this::number).start();
        new Thread(this::fizz).start();
        new Thread(this::buzz).start();
        new Thread(this::fizzbuzz).start();

        System.out.println("Result - " + result);
    }

    public synchronized void number() {
        while (currentNumber <= endNumber) {
            addToResult(String.valueOf(currentNumber));
            if (currentNumber % 3 == 0 || currentNumber % 5 == 0) {
                waiting();
            }
        }
    }

    public synchronized void fizz() {
        while (currentNumber <= endNumber) {
            if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                addToResult("fizz");
            } else {
                waiting();
            }
        }
    }

    public synchronized void buzz() {
        while (currentNumber <= endNumber) {
            if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                addToResult("buzz");
            } else {
                waiting();
            }
        }
    }

    public synchronized void fizzbuzz() {
        while (currentNumber <= endNumber) {
            if (currentNumber % 15 == 0) {
                addToResult("fizzbuzz");
            } else {
                waiting();
            }
        }
    }

    private void waiting() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void addToResult(String value) {
        result.add(String.valueOf(value));
        currentNumber++;
        notifyAll();
    }
}
