package com.github.wellch4n.silver.bullet.lock.redis

import org.redisson.api.RedissonClient

/**
 * @author wellCh4n
 * @date 2023/9/21
 */
interface RedisServerInit {
    fun init(options: RedisLockOptions): RedissonClient
}