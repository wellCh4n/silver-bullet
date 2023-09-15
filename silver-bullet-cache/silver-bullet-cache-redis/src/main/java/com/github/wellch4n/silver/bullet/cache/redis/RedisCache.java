package com.github.wellch4n.silver.bullet.cache.redis;

import com.github.wellch4n.silver.bullet.cache.api.Cache;
import com.github.wellch4n.silver.bullet.cache.api.CacheOptions;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.concurrent.Callable;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class RedisCache extends Cache<String> {

    private final Jedis jedis;

    public RedisCache(CacheOptions options) {
        super(options);
        if (options instanceof RedisCacheOptions) {
            RedisCacheOptions rCacheOptions = (RedisCacheOptions) options;
            jedis = new Jedis(rCacheOptions.getHost(), rCacheOptions.getPort());
            return;
        }
        throw new RuntimeException();
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public String get(String key, Callable<String> loader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(String key, String value) {
        this.jedis.set(key, value);
    }
}
