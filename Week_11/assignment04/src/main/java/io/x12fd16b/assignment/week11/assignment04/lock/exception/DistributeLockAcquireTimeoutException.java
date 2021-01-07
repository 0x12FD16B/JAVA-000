package io.x12fd16b.assignment.week11.assignment04.lock.exception;

/**
 * 分布式锁获取超时异常.
 *
 * @author David Liu
 */
public class DistributeLockAcquireTimeoutException extends RuntimeException {
    private static final long serialVersionUID = -4783668010792193475L;

    public DistributeLockAcquireTimeoutException() {
        super("acquire lock timeout.");
    }
}
