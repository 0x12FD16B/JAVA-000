package course.java.assignment.useSemaphore;

import course.java.assignment.FibCalculator;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 包装任务
 *
 * @author David Liu
 */
public class AsyncTask {
    private final Semaphore semaphore = new Semaphore(1);

    private volatile Integer res = null;

    private volatile boolean resultCalculated = false;

    public void sum(int num) throws InterruptedException {
        semaphore.acquire();
        res = FibCalculator.getInst().fib(num);
        resultCalculated = true;
        semaphore.release();
    }

    public int getRes() throws InterruptedException {
        int result;
        while (!resultCalculated) ;
        semaphore.acquire();
        result = this.res;
        semaphore.release();
        return result;
    }
}
