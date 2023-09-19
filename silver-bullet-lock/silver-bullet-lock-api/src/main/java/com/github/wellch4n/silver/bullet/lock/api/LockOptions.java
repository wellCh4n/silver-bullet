package com.github.wellch4n.silver.bullet.lock.api;

/**
 * @author wellCh4n
 * @date 2023/9/15
 */
public abstract class LockOptions {
    public abstract Class<? extends Lock> clazz();
}
