package course.java.assignment.useSynchronized;

/**
 * 主线程和异步线程通过 synchronized 同步
 *
 * @author David Liu
 */
public class RunningFacade {

    public static void main(String[] args) throws InterruptedException {
        AsyncTask task = new AsyncTask();
        new Thread(() -> task.sum(35)).start();
        System.out.println(task.getRes());
    }
}
