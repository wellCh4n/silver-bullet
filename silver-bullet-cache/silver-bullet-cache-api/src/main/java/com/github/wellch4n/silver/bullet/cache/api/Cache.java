package com.github.wellch4n.silver.bullet.cache.api;

import java.util.concurrent.Callable;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public abstract class Cache<V> {

    public Cache(CacheOptions options) {}

    public abstract V get(String key);

    public abstract V get(String key, Callable<V> loader);

    public abstract void set(String key, V value);
}
