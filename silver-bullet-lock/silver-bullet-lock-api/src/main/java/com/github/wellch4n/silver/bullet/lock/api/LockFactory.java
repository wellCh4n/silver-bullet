package com.github.wellch4n.silver.bullet.lock.api;

import java.lang.reflect.Constructor;

/**
 * @author wellCh4n
 * @date 2023/9/15
 */
public class LockFactory {
    public static Lock newInstance(LockOptions options) {
        try {
            Class<? extends Lock> clazz = options.clazz();
            Constructor<? extends Lock> constructor = clazz.getConstructor(LockOptions.class);
            return constructor.newInstance(options);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
