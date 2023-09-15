package com.github.wellch4n.silver.bullet.cache.api;

import java.lang.reflect.Constructor;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class CacheFactory {
    public static <V> Cache<V> newInstance(CacheOptions options) {
        try {
            Class<? extends Cache> clazz = options.clazz();
            Constructor<? extends Cache> constructor = clazz.getConstructor(CacheOptions.class);
            return constructor.newInstance(options);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
