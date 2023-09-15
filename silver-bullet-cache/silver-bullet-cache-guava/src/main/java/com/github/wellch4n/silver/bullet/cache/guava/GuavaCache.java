package com.github.wellch4n.silver.bullet.cache.guava;

import com.github.wellch4n.silver.bullet.cache.api.Cache;
import com.github.wellch4n.silver.bullet.cache.api.CacheOptions;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class GuavaCache<V> extends Cache<V> {

    com.google.common.cache.Cache<String, V> gCache = CacheBuilder.newBuilder().build();

    public GuavaCache(CacheOptions options) {
        super(options);
    }

    @Override
    public V get(String key) {
        return gCache.getIfPresent(key);
    }

    @Override
    public V get(String key, Callable<V> loader) {
        try {
            return gCache.get(key, loader);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void set(String key, V value) {
        gCache.put(key, value);
    }
}
