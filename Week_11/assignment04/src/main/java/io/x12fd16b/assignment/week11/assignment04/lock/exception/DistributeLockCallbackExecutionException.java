package io.x12fd16b.assignment.week11.assignment04.lock.exception;

/**
 * 分布式锁回调执行异常.
 *
 * @author David Liu
 */
public class DistributeLockCallbackExecutionException extends RuntimeException {
    private static final long serialVersionUID = -7878071901055457416L;

    public DistributeLockCallbackExecutionException(String message) {
        super(message);
    }
}
