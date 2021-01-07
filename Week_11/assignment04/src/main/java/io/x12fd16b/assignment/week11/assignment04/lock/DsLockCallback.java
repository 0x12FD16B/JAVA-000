package io.x12fd16b.assignment.week11.assignment04.lock;

/**
 * DsLockCallback.
 *
 * @author David Liu
 */
@FunctionalInterface
public interface DsLockCallback {
    DsLockCallbackResult execute();
}
