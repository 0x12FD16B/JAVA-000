package course.java.assignment.useCyclicBarrier;

import course.java.assignment.FibCalculator;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用 CyclicBarrier
 *
 * @author David Liu
 */
public class AsyncTask {
    private CyclicBarrier barrier;

    private volatile Integer res = null;

    public AsyncTask() {
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void sum(int num) throws BrokenBarrierException, InterruptedException {
        res = FibCalculator.getInst().fib(num);
        barrier.await();
    }

    public int getRes() throws InterruptedException {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }
}
