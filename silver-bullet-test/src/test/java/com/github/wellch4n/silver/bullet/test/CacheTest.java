package com.github.wellch4n.silver.bullet.test;

import com.github.wellch4n.silver.bullet.cache.api.Cache;
import com.github.wellch4n.silver.bullet.cache.api.CacheFactory;
import com.github.wellch4n.silver.bullet.cache.guava.GuavaCacheOptions;
import com.github.wellch4n.silver.bullet.cache.redis.RedisCacheOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class CacheTest {
    @Test
    public void testGuava() {
        GuavaCacheOptions guavaCacheOptions = new GuavaCacheOptions();
        Cache<Object> cache = CacheFactory.newInstance(guavaCacheOptions);
        cache.set("1", "2");
        Assertions.assertEquals("2", cache.get("1"));
    }

    @Test
    public void testRedis() {
        RedisCacheOptions redisCacheOptions = new RedisCacheOptions();
        redisCacheOptions.setHost("127.0.0.1");
        redisCacheOptions.setPort(6379);
        Cache<String> redisCache = CacheFactory.newInstance(redisCacheOptions);
        redisCache.set("1", "2");
        Assertions.assertEquals("2", redisCache.get("1"));
    }
}
