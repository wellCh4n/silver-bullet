package com.github.wellch4n.silver.bullet.database.jdbc;

import com.github.wellch4n.silver.bullet.database.api.Database;
import com.github.wellch4n.silver.bullet.database.api.DatabaseOptions;

import java.sql.Driver;


/**
 * @author wellCh4n
 * @date 2023/9/11
 */
public class JDBCDatabaseOptions extends DatabaseOptions {
    @Override
    public Class<? extends Database> clazz() {
        return JDBCDatabase.class;
    }

    private Class<? extends Driver> driver;
    private String url;
    private String username;
    private String password;

    public Class<? extends Driver> getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        try {
            this.driver = (Class<? extends Driver>) Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void setDriver(Class<? extends Driver> driverClazz) {
        this.driver = driverClazz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
