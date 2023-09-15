package com.github.wellch4n.silver.bullet.database.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;

/**
 * @author wellCh4n
 * @date 2023/9/11
 */
public class DatabaseFactory {

    public static Database build(DatabaseOptions options) {
        try {
            Class<? extends Database> clazz = options.clazz();
            Constructor<? extends Database> constructor = clazz.getConstructor(DatabaseOptions.class);
            return constructor.newInstance(options);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}