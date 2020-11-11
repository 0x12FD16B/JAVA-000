package course.java.assignment.useThreadJoin;

/**
 * 方法一: 定义一个任务线程, 在线程中维护一个成员变量作为返回值, 在主线程中, 利用 Thread#join 方法等待任务线程执行完成
 *
 * @author David Liu
 */
public class CommandLineRunner {

    public static void main(String[] args) throws InterruptedException {
        CallableThread thread = new CallableThread();
        thread.start();
        thread.join();
        System.out.println(thread.getResult());
    }
}
