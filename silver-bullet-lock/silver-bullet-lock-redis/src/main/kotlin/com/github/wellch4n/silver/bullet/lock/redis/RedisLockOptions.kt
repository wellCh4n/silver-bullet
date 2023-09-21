package com.github.wellch4n.silver.bullet.lock.redis

import com.github.wellch4n.silver.bullet.lock.api.Lock
import com.github.wellch4n.silver.bullet.lock.api.LockOptions

/**
 * @author wellCh4n
 * @date 2023/9/21
 */
data class RedisLockOptions(val server: RedisServer, val nodes: Set<String>) : LockOptions() {
    override fun clazz(): Class<out Lock> {
        return RedisLock::class.java
    }
}
