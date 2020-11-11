package course.java.assignment.useCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用 CyclicBarrier
 *
 * @author David Liu
 */
public class RunningFacade {
    public static void main(String[] args) {
        AsyncTask task = new AsyncTask();

        CyclicBarrier barrier = new CyclicBarrier(1, () -> {
            int result = 0;
            try {
                result = task.getRes();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        });


        task.setBarrier(barrier);

        Thread thread = new Thread(() -> {
            try {
                task.sum(35);
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
