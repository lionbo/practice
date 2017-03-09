package org.lionbo.practice.distributeLock;

import java.util.concurrent.TimeUnit;

public interface DistributeLock {

    public boolean tryLock(long tryLockTimeout, TimeUnit tryLockTimeUnit, int retryTimes);

    public void unlock();

}
