public class PrintOddEvenNumber {
    // this is monitor lock
    private static final Object lock = new Object();
    private static int count = 1;
    private static final int MAX_COUNT = 10;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printOdd());
        Thread t2 = new Thread(() -> printEven());
        t1.start();
        t2.start();
    }

    public static void printOdd() {
        // in synchronized block, only the thread having monitor lock can access that block
        synchronized (lock) {
            while (count < MAX_COUNT) {
                while (count % 2 == 0) {
                    try {
                        lock.wait(); // will give monitor lock to other thread which is running in the process
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // will be executed when count%2 !=0 and other thread has invoked notify method to awake this sleeping thread
                System.out.println("Odd number: " + count);
                count++;
                lock.notify(); // will now awake other even running thread, with updated counter variable
            }
        }
    }

    public static void printEven() {
        synchronized (lock) {
            while (count < MAX_COUNT) {
                while (count % 2 != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Even number: " + count);
                count++;
                lock.notify();
            }
        }
    }
}
