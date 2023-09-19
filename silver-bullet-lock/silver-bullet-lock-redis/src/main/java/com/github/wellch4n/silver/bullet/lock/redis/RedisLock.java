package com.github.wellch4n.silver.bullet.lock.redis;

import com.github.wellch4n.silver.bullet.lock.api.Lock;
import com.github.wellch4n.silver.bullet.lock.api.LockOptions;
import com.github.wellch4n.silver.bullet.lock.api.LockTime;
import com.github.wellch4n.silver.bullet.lock.api.WaitTime;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;

import java.util.Optional;

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
    public void lock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.lock();
    }

    @Override
    public void lock(String key, LockTime lockTime) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(lockTime.getTime(), lockTime.getUnit());
    }

    @Override
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

    @Override
    public boolean tryLock(String key, WaitTime waitTime) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime.getTime(), waitTime.getUnit());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryLock(String key, LockTime lockTime, WaitTime waitTime) {
        RLock lock = redissonClient.getLock(key);
        try {
            if (lockTime.getUnit() == waitTime.getUnit()) {
                return lock.tryLock(waitTime.getTime(), lockTime.getTime(), waitTime.getUnit());
            }

            // based waitTime unit
            long lockTimeBasedWaitTimeUnitValue = lockTime.getUnit().convert(lockTime.getTime(), waitTime.getUnit());
            return lock.tryLock(waitTime.getTime(), lockTimeBasedWaitTimeUnitValue, waitTime.getUnit());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
