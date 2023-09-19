package com.github.wellch4n.silver.bullet.lock.api;

import java.util.concurrent.TimeUnit;

/**
 * @author wellCh4n
 * @date 2023/9/19
 */
public class LockTime {
    private final Long time;
    private final TimeUnit unit;

    public LockTime(Long time, TimeUnit unit) {
        this.time = time;
        this.unit = unit;
    }

    public LockTime(Integer time, TimeUnit unit) {
        this.time = time.longValue();
        this.unit = unit;
    }

    public static LockTime of(Long time, TimeUnit unit) {
        return new LockTime(time, unit);
    }

    public static LockTime of(Integer time, TimeUnit unit) {
        return new LockTime(time, unit);
    }

    public Long getTime() {
        return time;
    }

    public TimeUnit getUnit() {
        return unit;
    }
}
