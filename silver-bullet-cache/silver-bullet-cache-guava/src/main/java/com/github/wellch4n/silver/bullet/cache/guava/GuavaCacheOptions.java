package com.github.wellch4n.silver.bullet.cache.guava;

import com.github.wellch4n.silver.bullet.cache.api.Cache;
import com.github.wellch4n.silver.bullet.cache.api.CacheOptions;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class GuavaCacheOptions extends CacheOptions {

    @Override
    public Class<? extends Cache> clazz() {
        return GuavaCache.class;
    }
}
