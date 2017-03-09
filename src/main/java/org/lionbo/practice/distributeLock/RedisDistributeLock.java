package org.lionbo.practice.distributeLock;

import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @version 0.1
 * @author lionbo
 */
public final class RedisDistributeLock implements DistributeLock, Closeable {

    private static final Logger LOG = LoggerFactory.getLogger(RedisDistributeLock.class);

    private ShardedJedisPool jedisPool;
    private long expireTime = 30;
    private TimeUnit expireTimeUnit = TimeUnit.SECONDS;
    private long tryLockTimeout = 20;
    private TimeUnit tryLockTimeUnit = TimeUnit.MICROSECONDS;
    private int retryTimes = 3;
    private String distributeLockKey;

    private String placeHolder = UUID.randomUUID().toString().replaceAll("-", "");

    /**
     * @param distributeLockKey
     * @param jedisPool
     */
    public RedisDistributeLock(String distributeLockKey, ShardedJedisPool jedisPool) {
        this.jedisPool = jedisPool;
        this.distributeLockKey = distributeLockKey;
    }

    /**
     * 基于redis的分布式锁
     * @param distributeLockKey
     * @param jedisPool
     * @param expireTime
     * @param expireTimeUnit
     */
    public RedisDistributeLock(String distributeLockKey, ShardedJedisPool jedisPool, long expireTime,
            TimeUnit expireTimeUnit) {
        this.jedisPool = jedisPool;
        this.expireTime = expireTime;
        this.expireTimeUnit = expireTimeUnit;
        this.distributeLockKey = distributeLockKey;
    }

    /**
     * @param distributeLockKey
     * @param jedisPool
     * @param expireTime
     * @param expireTimeUnit
     * @param tryLockTimeout
     * @param tryLockTimeUnit
     * @param retryTimes
     */
    public RedisDistributeLock(String distributeLockKey, ShardedJedisPool jedisPool, long expireTime,
            TimeUnit expireTimeUnit, long tryLockTimeout, TimeUnit tryLockTimeUnit, int retryTimes) {
        this.jedisPool = jedisPool;
        this.expireTime = expireTime;
        this.expireTimeUnit = expireTimeUnit;
        this.distributeLockKey = distributeLockKey;
    }

    public boolean tryLock() {
        return this.tryLock(tryLockTimeout, tryLockTimeUnit, retryTimes);
    }

    @Override
    public boolean tryLock(long tryLockTimeout, TimeUnit tryLockTimeUnit, int retryTimes) {
        Assert.isTrue(tryLockTimeout > 0, "tryLockTimeout must lager than zero");
        Assert.notNull(distributeLockKey, "distributeLockKey is null");
        long millis = tryLockTimeUnit.toMillis(tryLockTimeout);
        long sleepMillis = millis / retryTimes;
        sleepMillis = (sleepMillis == 0) ? 1 : sleepMillis;
        try (ShardedJedis jedis = jedisPool.getResource()) {

            for (int i = 0; i < retryTimes; i++) {
                String value = jedis.get(distributeLockKey);

                if (StringUtils.isNoneBlank(value) && !(value.equals(placeHolder))) {
                    // 有锁,placeHolder不同 其他实例抢到锁，继续重试;
                } else if (StringUtils.isNoneBlank(value) && value.equals(placeHolder)) {
                    // 有锁，placeHolder相同，重入锁
                    return true;
                } else {
                    // 锁超时或者没有锁
                    String result = jedis.set(distributeLockKey, placeHolder, "nx", "px",
                            expireTimeUnit.toMillis(expireTime));

                    if ("OK".equalsIgnoreCase(result)) {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("" + distributeLockKey + " get lock!");
                        }
                        return true;
                    }
                }

                if (i != (retryTimes - 1)) {
                    try {
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e) {
                        LOG.warn("tryLock Interrupted", e);
                    }
                }

            }
        }

        return false;
    }

    @Override
    public void unlock() {
        try (ShardedJedis jedis = jedisPool.getResource()) {
            String placeHolder = jedis.get(distributeLockKey);
            if (StringUtils.isNotBlank(placeHolder) && placeHolder.equals(this.placeHolder)) {
                jedis.del(distributeLockKey);
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("" + distributeLockKey + " unlock!");
            }
        }
    }

    @Override
    public void close() throws IOException {
        this.unlock();
    }
}
