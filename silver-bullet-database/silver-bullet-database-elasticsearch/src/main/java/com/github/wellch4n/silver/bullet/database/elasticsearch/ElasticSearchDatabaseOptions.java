package com.github.wellch4n.silver.bullet.database.elasticsearch;

import com.github.wellch4n.silver.bullet.database.api.Database;
import com.github.wellch4n.silver.bullet.database.api.DatabaseOptions;
import org.apache.http.HttpHost;

/**
 * @author wellCh4n
 * @date 2023/9/12
 */
public class ElasticSearchDatabaseOptions extends DatabaseOptions {

    private HttpHost[] hosts;

    public HttpHost[] getHosts() {
        return hosts;
    }

    public void setHosts(HttpHost[] hosts) {
        this.hosts = hosts;
    }

    @Override
    public Class<? extends Database> clazz() {
        return ElasticSearchDatabase.class;
    }
}
