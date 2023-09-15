package com.github.wellch4n.silver.bullet.database.api;

import java.util.concurrent.Callable;

/**
 * @author wellCh4n
 * @date 2023/9/11
 */
public abstract class Database {
    public Database(DatabaseOptions options) {}

    public abstract <T> T getRepository(Class<T> t);

    public abstract <T> T executeTx(Callable<T> callable);
}
