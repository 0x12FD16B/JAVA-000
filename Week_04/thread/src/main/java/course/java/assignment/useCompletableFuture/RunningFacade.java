package course.java.assignment.useCompletableFuture;

import course.java.assignment.FibCalculator;

import java.util.concurrent.CompletableFuture;

/**
 * 使用 CompletableFuture
 *
 * @author David Liu
 */
public class RunningFacade {

    public static void main(String[] args) {
        int result = CompletableFuture.supplyAsync(() -> FibCalculator.getInst().fib(35)).join();
        System.out.println(result);
    }
}
