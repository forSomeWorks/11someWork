package ua.goit;

public class Task1 {


    public static void main(String[] args) throws InterruptedException {

        long timeOfProgramWorksInMilliSec = 10000;

        Thread threadOneSecond = new Thread(() -> {
            for (int i = 0; i < timeOfProgramWorksInMilliSec; i += 1000) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Passed 1 second " + "(running in " + Thread.currentThread().getName() + ")");
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        });

        Thread threadFiveSeconds = new Thread(() -> {
            for (int i = 0; i < timeOfProgramWorksInMilliSec; i += 5000) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Passed 5 seconds " + "(running in " + Thread.currentThread().getName() + ")");
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        });

        threadOneSecond.start();
        threadFiveSeconds.start();
    }
}
