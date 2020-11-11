package course.java.assignment.useCountDownLatch;

import course.java.assignment.FibCalculator;

import java.util.concurrent.CountDownLatch;

/**
 * 使用 CountDownLatch
 *
 * @author David Liu
 */
public class AsyncTask {
    private final CountDownLatch latch;
    private volatile Integer res = null;

    public AsyncTask(CountDownLatch latch) {
        this.latch = latch;
    }

    public void sum(int num) {
        res = FibCalculator.getInst().fib(num);
        latch.countDown();
    }

    public int getRes() throws InterruptedException {
        latch.await();
        return res;
    }
}
