package io.x12fd16b.assignment.week11.assignment04.lock.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.x12fd16b.assignment.week11.assignment04.lock.DsLock;
import io.x12fd16b.assignment.week11.assignment04.lock.DsLockCallback;
import io.x12fd16b.assignment.week11.assignment04.lock.DsLockCallbackResult;
import io.x12fd16b.assignment.week11.assignment04.lock.exception.DistributeLockAcquireTimeoutException;
import io.x12fd16b.assignment.week11.assignment04.lock.exception.DistributeLockCallbackExecutionException;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Redis Based Distribute Lock.
 *
 * @author David Liu
 */
public class RedisDsLock implements DsLock {

    private final RedisClient redisClient;

    private final RedisDsLockProperties lockProperties;

    private final Map<String, Timer> leaseTaskMap = new ConcurrentHashMap<>();

    public RedisDsLock(final RedisClient redisClient, final RedisDsLockProperties lockProperties) {
        this.redisClient = redisClient;
        this.lockProperties = lockProperties;
    }

    @Override
    public void executeCallbackWithLock(String lockResource, int acquireTimeout, TimeUnit timeUnit, DsLockCallback callback) {
        final long acquireLockTimeoutSeconds = timeUnit.convert(acquireTimeout, TimeUnit.SECONDS);
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisCommands<String, String> sync = connection.sync();
            if (!this.doAcquireLock(sync, lockResource, lockProperties.getInstanceIdentity(), acquireLockTimeoutSeconds)) {
                final long acquireTimeoutMills = timeUnit.convert(acquireTimeout, TimeUnit.MILLISECONDS);
                final long startSpinMills = System.currentTimeMillis();
                try {
                    while (true) {
                        if (this.doAcquireLock(sync, lockResource, lockProperties.getInstanceIdentity(), acquireLockTimeoutSeconds)) {
                            break;
                        } else {
                            long spinDurationMills = System.currentTimeMillis() - startSpinMills;
                            if (spinDurationMills >= acquireTimeoutMills) {
                                throw new DistributeLockAcquireTimeoutException();
                            } else {
                                TimeUnit.MILLISECONDS.sleep(lockProperties.getAcquireLockSpinIntervalMills());
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new DistributeLockCallbackExecutionException(e.getMessage());
                }
            }
            DsLockCallbackResult callbackResult = callback.execute();
            if (!callbackResult.isSuccess()) {
                throw new DistributeLockCallbackExecutionException(callbackResult.getErrorInfo());
            }
            this.doReleaseLock(sync, lockResource);
        }
    }

    private boolean doAcquireLock(RedisCommands<String, String> redisCommands, String lockResource, String instanceIdentity, long acquireTimeoutSeconds) {
        boolean result = "OK".equals(redisCommands.set(lockResource, instanceIdentity, SetArgs.Builder.nx().ex(acquireTimeoutSeconds)));
        if (!result) return false;
        Timer timer = new Timer();
        timer.schedule(new LockRenewLeaseTimerTask(redisCommands, lockResource, instanceIdentity, acquireTimeoutSeconds), 0, lockProperties.getCheckLockLeaseIntervalMills());
        this.leaseTaskMap.put(String.valueOf(Thread.currentThread().getId()), timer);
        return true;
    }

    private void doReleaseLock(RedisCommands<String, String> redisCommands, String lockResource) {
        String threadId = String.valueOf(Thread.currentThread().getId());
        this.leaseTaskMap.get(threadId).cancel();
        this.leaseTaskMap.remove(threadId);
        redisCommands.del(lockResource);
    }

    private static class LockRenewLeaseTimerTask extends TimerTask {

        private final RedisCommands<String, String> redisCommands;

        private final String lockResource;

        private final long leaseSeconds;

        private final String instanceIdentity;

        private LockRenewLeaseTimerTask(final RedisCommands<String, String> redisCommands, final String lockResource, final String instanceIdentity, final long leaseSeconds) {
            this.redisCommands = redisCommands;
            this.lockResource = lockResource;
            this.instanceIdentity = instanceIdentity;
            this.leaseSeconds = leaseSeconds;
        }

        @Override
        public void run() {
            redisCommands.set(lockResource, instanceIdentity, SetArgs.Builder.xx().ex(leaseSeconds));
        }
    }
}
