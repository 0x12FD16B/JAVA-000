package course.java.assignment.useLockCondition;

import course.java.assignment.FibCalculator;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用 Lock 和 Condition
 *
 * @author David Liu
 */
public class AsyncTask {
    private final Lock lock = new ReentrantLock();

    private final Condition calComplete = lock.newCondition();

    private volatile Integer res = null;

    public void sum(int num) {
        lock.lock();
        try {
            res = FibCalculator.getInst().fib(35);
            calComplete.signal();
        } finally {
            lock.unlock();
        }
    }


    public int getRes() throws InterruptedException {
        lock.lock();
        try {
            while (res == null) {
                calComplete.await();
            }
        } finally {
            lock.unlock();
        }
        return res;
    }
}
