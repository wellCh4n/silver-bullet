package com.github.wellch4n.silver.bullet.cache.redis;

import com.github.wellch4n.silver.bullet.cache.api.Cache;
import com.github.wellch4n.silver.bullet.cache.api.CacheOptions;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class RedisCacheOptions extends CacheOptions {
    private String host;
    private Integer port;

    @Override
    public Class<? extends Cache<?>> clazz() {
        return RedisCache.class;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
