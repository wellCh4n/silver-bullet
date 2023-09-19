package com.github.wellch4n.silver.bullet.lock.redis;

import com.github.wellch4n.silver.bullet.lock.api.Lock;
import com.github.wellch4n.silver.bullet.lock.api.LockFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

/**
 * @author wellCh4n
 * @date 2023/9/18
 */
public class RedisLockTest {

    private static Lock lock;

    @BeforeAll
    public static void startup() {
        RedisLockOptions redisLockOptions = new RedisLockOptions();
        redisLockOptions.setServerType(RedisServerType.SINGLE);
        redisLockOptions.setNodes("redis://127.0.0.1:6379");
        lock = LockFactory.newInstance(redisLockOptions);
    }

    @Test
    public void testLock() throws Exception {
        Thread thread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getId() + " first thread start");
                System.out.println(Thread.currentThread().getId() + " start get lock");
                lock.lock("testLock2");
                System.out.println(Thread.currentThread().getId() + " got lock");
                System.out.println(Thread.currentThread().getId() + " start doBiz");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getId() + " finish biz");
                lock.unlock("testLock2");
                System.out.println(Thread.currentThread().getId() + " unlocked");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread aThread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getId() + " second thread start");
                System.out.println(Thread.currentThread().getId() + " start get lock");
                lock.lock("testLock2");
                System.out.println(Thread.currentThread().getId() + " got lock");
                System.out.println(Thread.currentThread().getId() + " start doBiz");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getId() + " finish biz");
                lock.unlock("testLock2");
                System.out.println(Thread.currentThread().getId() + " unlocked");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        aThread.start();

        thread.join();
        aThread.join();
    }
}
