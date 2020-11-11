package course.java.assignment.useLockCondition;

/**
 * 利用 Lock 和 Condition 多多线程同步
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
