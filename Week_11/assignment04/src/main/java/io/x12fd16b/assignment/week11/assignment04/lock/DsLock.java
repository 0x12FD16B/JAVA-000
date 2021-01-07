package io.x12fd16b.assignment.week11.assignment04.lock;

import java.util.concurrent.TimeUnit;

/**
 * Distribute Lock Interface.
 *
 * @author David Liu
 */
public interface DsLock {

    void executeCallbackWithLock(String lockResource, int acquireTimeout, TimeUnit timeUnit, DsLockCallback callback);
}
