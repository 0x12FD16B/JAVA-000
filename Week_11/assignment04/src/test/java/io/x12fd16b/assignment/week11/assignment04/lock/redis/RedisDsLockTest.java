package io.x12fd16b.assignment.week11.assignment04.lock.redis;

import io.lettuce.core.RedisClient;
import io.x12fd16b.assignment.week11.assignment04.lock.DsLock;
import io.x12fd16b.assignment.week11.assignment04.lock.DsLockCallbackResult;
import io.x12fd16b.assignment.week11.assignment04.lock.exception.DistributeLockAcquireTimeoutException;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisDsLockTest {

    @Test
    public void testExecuteCallbackWithLock() {
        RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379");
        RedisDsLockProperties redisDsLockProperties = new RedisDsLockProperties();
        redisDsLockProperties.setCheckLockLeaseIntervalMills(1000);
        redisDsLockProperties.setAcquireLockSpinIntervalMills(200);
        redisDsLockProperties.setInstanceIdentity(UUID.randomUUID().toString());
        DsLock dsLock = new RedisDsLock(redisClient, redisDsLockProperties);
        String lockResource = "lock";
        dsLock.executeCallbackWithLock(lockResource, 10, TimeUnit.SECONDS, () -> {
            System.out.println("in ds lock");
            DsLockCallbackResult result = new DsLockCallbackResult();
            result.setSuccess(true);
            return result;
        });
    }

    @Test
    public void testExecuteCallbackWithLockTimeout() {
        RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379");
        RedisDsLockProperties redisDsLockProperties = new RedisDsLockProperties();
        redisDsLockProperties.setCheckLockLeaseIntervalMills(1000);
        redisDsLockProperties.setAcquireLockSpinIntervalMills(200);
        redisDsLockProperties.setInstanceIdentity(UUID.randomUUID().toString());
        DsLock dsLock = new RedisDsLock(redisClient, redisDsLockProperties);
        String lockResource = "lock";

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                dsLock.executeCallbackWithLock(lockResource, 3, TimeUnit.SECONDS, () -> {
                    System.out.println("thread acquire lock");
                    DsLockCallbackResult result = new DsLockCallbackResult();
                    result.setSuccess(true);
                    return result;
                });
            } catch (Exception e) {
                Assert.assertTrue(e instanceof DistributeLockAcquireTimeoutException);
            }
        }).start();

        dsLock.executeCallbackWithLock(lockResource, 3, TimeUnit.SECONDS, () -> {
            try {
                System.out.println("main acquire lock");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("in ds lock");
            DsLockCallbackResult result = new DsLockCallbackResult();
            result.setSuccess(true);
            return result;
        });
    }
}