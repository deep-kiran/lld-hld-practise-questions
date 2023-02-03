package ratelimiter;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class RateLimiterTest {
    private RateLimiterWithConcurrencyHandeling rateLimiter = new RateLimiterWithConcurrencyHandeling();

    @Test
    public void testRateLimit() throws InterruptedException {
        int numThreads = 100;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            int customerId = i;
            new Thread(() -> {
                try {
                    startSignal.await();
                    rateLimiter.rateLimit(customerId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    doneSignal.countDown();
                }
            }).start();
        }

        startSignal.countDown();
        doneSignal.await();

        // Add your assertions here to check the results of the test
    }
}
