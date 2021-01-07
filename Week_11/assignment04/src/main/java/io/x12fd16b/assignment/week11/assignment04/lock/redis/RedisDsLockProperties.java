package io.x12fd16b.assignment.week11.assignment04.lock.redis;

import lombok.Data;

/**
 * Redis base distribute lock properties
 *
 * @author David Liu
 */
@Data
public final class RedisDsLockProperties {

    /**
     * 获取锁自旋间隔时间.
     */
    private int acquireLockSpinIntervalMills;

    /**
     * 检查锁是否需要续期间隔时长.
     */
    private int checkLockLeaseIntervalMills;

    /**
     * 实例标识符.
     */
    private String instanceIdentity;
}
