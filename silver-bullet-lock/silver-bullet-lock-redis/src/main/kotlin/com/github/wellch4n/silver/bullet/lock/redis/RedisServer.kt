package com.github.wellch4n.silver.bullet.lock.redis

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config

/**
 * @author wellCh4n
 * @date 2023/9/20
 */
enum class RedisServer : RedisServerInit {
    SINGLE {
        override fun init(options: RedisLockOptions): RedissonClient {
            val node = options.nodes.first()
            val config = Config()
            config.useSingleServer().setAddress(node)
            return Redisson.create(config)
        }
    },
    CLUSTER {
        override fun init(options: RedisLockOptions): RedissonClient {
            val nodeArray = options.nodes.toTypedArray()
            val config = Config()
            config.useClusterServers().addNodeAddress(*nodeArray)
            return Redisson.create(config)
        }
    }
}