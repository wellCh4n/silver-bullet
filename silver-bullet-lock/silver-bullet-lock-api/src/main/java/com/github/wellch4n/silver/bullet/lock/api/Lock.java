package com.github.wellch4n.silver.bullet.lock.api;

/**
 * @author wellCh4n
 * @date 2023/9/15
 */
public abstract class Lock {

    public Lock(LockOptions options){}

    public abstract void lock(String key);

    public abstract void lock(String key, LockTime lockTime);

    public abstract void unlock(String key);

    public abstract boolean tryLock(String key, WaitTime waitTime);

    public abstract boolean tryLock(String key, LockTime lockTime, WaitTime waitTime);
}
