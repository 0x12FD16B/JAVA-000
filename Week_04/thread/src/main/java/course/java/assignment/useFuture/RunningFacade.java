package course.java.assignment.useFuture;

import course.java.assignment.FibCalculator;

import java.util.concurrent.*;

/**
 * 提交任务到线程池, 通过 Future 获取运行结果
 *
 * @author David Liu
 */
public class RunningFacade {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Object> task = () -> FibCalculator.getInst().fib(35);
        Future<Object> future = executorService.submit(task);
        System.out.println(future.get());
        executorService.shutdown();
    }
}
