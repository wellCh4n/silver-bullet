package com.github.wellch4n.silver.bullet.lock.api;

import java.util.concurrent.TimeUnit;

/**
 * @author wellCh4n
 * @date 2023/9/15
 */
public abstract class Lock {

    public Lock(LockOptions options){}

    public abstract boolean lock(String key);

    public abstract void unlock(String key);

    public abstract boolean tryLock(String key, long time, TimeUnit unit);
}
