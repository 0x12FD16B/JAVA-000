package course.java.assignment.useSynchronized;

import course.java.assignment.FibCalculator;

/**
 * 异步任务
 *
 * @author David Liu
 */
public class AsyncTask {

    private volatile Integer res = null;

    public synchronized void sum(int num) {
        res = FibCalculator.getInst().fib(num);
        notifyAll();
    }

    public synchronized int getRes() throws InterruptedException {
        while (res == null) {
            wait();
        }
        return res;
    }
}
