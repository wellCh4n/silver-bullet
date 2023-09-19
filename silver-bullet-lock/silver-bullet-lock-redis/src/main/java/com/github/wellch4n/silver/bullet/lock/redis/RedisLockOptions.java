package com.github.wellch4n.silver.bullet.lock.redis;

import com.github.wellch4n.silver.bullet.lock.api.Lock;
import com.github.wellch4n.silver.bullet.lock.api.LockOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wellCh4n
 * @date 2023/9/15
 */
public class RedisLockOptions extends LockOptions {

    private RedisServerType serverType;
    private Set<String> nodes;

    public RedisServerType getServerType() {
        return serverType;
    }

    public void setServerType(RedisServerType serverType) {
        this.serverType = serverType;
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public void setNodes(Set<String> nodes) {
        this.nodes = nodes;
    }

    public void setNodes(String... nodes) {
        this.nodes = Arrays.stream(nodes).collect(Collectors.toSet());
    }

    @Override
    public Class<? extends Lock> clazz() {
        return RedisLock.class;
    }
}
