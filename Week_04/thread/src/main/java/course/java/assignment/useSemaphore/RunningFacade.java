package course.java.assignment.useSemaphore;

/**
 * 使用 permits 为 1 的 semaphore 让任务线程和主线程同步
 *
 * @author David Liu
 */
public class RunningFacade {

    public static void main(String[] args) throws InterruptedException {
        AsyncTask task = new AsyncTask();
        new Thread(() -> {
            try {
                task.sum(35);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(task.getRes());
    }
}
