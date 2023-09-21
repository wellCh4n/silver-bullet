package com.github.wellch4n.silver.bullet.lock.redis

import com.github.wellch4n.silver.bullet.lock.api.Lock
import com.github.wellch4n.silver.bullet.lock.api.LockOptions
import com.github.wellch4n.silver.bullet.lock.api.LockTime
import com.github.wellch4n.silver.bullet.lock.api.WaitTime
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config

/**
 * @author wellCh4n
 * @date 2023/9/20
 */
class RedisLock(options: LockOptions) : Lock(options) {

    private val redissonClient: RedissonClient

    init {
        val redisLockOptions = options as RedisLockOptions
        val (serverType) = redisLockOptions
        this.redissonClient = serverType.init(redisLockOptions)
    }

    override fun lock(key: String) {
        val lock = redissonClient.getLock(key)
        lock.lock()
    }

    override fun lock(key: String, lockTime: LockTime) {
        val lock = redissonClient.getLock(key)
        lock.lock(lockTime.time, lockTime.unit)
    }

    override fun unlock(key: String) {
        val lock = redissonClient.getLock(key)
        lock.unlock()
    }

    override fun tryLock(key: String, waitTime: WaitTime): Boolean {
        val lock = redissonClient.getLock(key)
        return try {
            lock.tryLock(waitTime.time, waitTime.unit)
        } catch (e: InterruptedException) {
            throw java.lang.RuntimeException(e)
        }
    }

    override fun tryLock(key: String, lockTime: LockTime, waitTime: WaitTime): Boolean {
        val lock = redissonClient.getLock(key)
        return try {
            if (lockTime.unit === waitTime.unit) {
                return lock.tryLock(waitTime.time, lockTime.time, waitTime.unit)
            }

            // based waitTime unit
            val lockTimeBasedWaitTimeUnitValue = lockTime.unit.convert(lockTime.time, waitTime.unit)
            lock.tryLock(waitTime.time, lockTimeBasedWaitTimeUnitValue, waitTime.unit)
        } catch (e: InterruptedException) {
            throw java.lang.RuntimeException(e)
        }
    }
}