package course.java.assignment.useCountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 使用 CountDownLatch
 *
 * @author David Liu
 */
public class RunningFacade {

    public static void main(String[] args) throws InterruptedException {
        AsyncTask task = new AsyncTask(new CountDownLatch(1));
        Thread thread = new Thread(() -> task.sum(35));
        thread.start();
        System.out.println(task.getRes());
    }
}
