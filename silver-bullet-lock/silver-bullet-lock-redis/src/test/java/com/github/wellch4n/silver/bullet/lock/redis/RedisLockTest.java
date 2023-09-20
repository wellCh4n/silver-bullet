package com.github.wellch4n.silver.bullet.lock.redis;

import com.github.wellch4n.silver.bullet.lock.api.Lock;
import com.github.wellch4n.silver.bullet.lock.api.LockFactory;
import com.github.wellch4n.silver.bullet.lock.api.WaitTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wellCh4n
 * @date 2023/9/18
 */
public class RedisLockTest {

    private static Lock lock;

    @BeforeAll
    public static void startup() {
        Set<String> nodes = new HashSet<>();
        nodes.add("redis://127.0.0.1:6379");
        RedisLockOptions redisLockOptions = new RedisLockOptions(RedisServerType.SINGLE, nodes);
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

    @Test
    public void testTryLock() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getId() + " first thread start");
                System.out.println(Thread.currentThread().getId() + " first start get lock");
                lock.lock("testLock2");
                System.out.println(Thread.currentThread().getId() + " first got lock");
                System.out.println(Thread.currentThread().getId() + " first start doBiz");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " first finish biz");
                lock.unlock("testLock2");
                System.out.println(Thread.currentThread().getId() + " first unlocked");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread aThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " second thread start");
                System.out.println(Thread.currentThread().getId() + " second start get lock");
                boolean b = lock.tryLock("testLock2", WaitTime.of(5, TimeUnit.SECONDS));
                System.out.println(Thread.currentThread().getId() + " second got lock result=" + b);
                if (b) {
                    Thread.sleep(10000);
                    lock.unlock("testLock2");
                    System.out.println(Thread.currentThread().getId() + " second unlocked");
                }
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
