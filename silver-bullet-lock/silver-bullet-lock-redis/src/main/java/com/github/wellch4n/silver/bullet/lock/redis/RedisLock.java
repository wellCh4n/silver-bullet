package com.github.wellch4n.silver.bullet.lock.redis;

import com.github.wellch4n.silver.bullet.lock.api.Lock;
import com.github.wellch4n.silver.bullet.lock.api.LockOptions;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author wellCh4n
 * @date 2023/9/15
 */
public class RedisLock extends Lock {

    private final RedissonClient redissonClient;

    public RedisLock(LockOptions options) {
        super(options);
        RedisLockOptions redisLockOptions = (RedisLockOptions) options;

        RedisServerType serverType = redisLockOptions.getServerType();
        if (serverType == RedisServerType.SINGLE) {
            Optional<String> node = redisLockOptions.getNodes().stream().findFirst();
            if (node.isPresent()) {
                Config config = new Config();
                config.useSingleServer().setAddress(node.get());
                this.redissonClient = Redisson.create(config);
                return;
            } else {
                throw new RuntimeException();
            }
        }
        if (serverType == RedisServerType.CLUSTER) {
            Config config = new Config();
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            clusterServersConfig.addNodeAddress(redisLockOptions.getNodes().toArray(new String[]{}));
            this.redissonClient = Redisson.create(config);
        }
        throw new RuntimeException();
    }

    @Override
    public boolean lock(String key) {
        try {
            RLock lock = redissonClient.getLock(key);
            lock.lock();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

    @Override
    public boolean tryLock(String key, long time, TimeUnit unit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
