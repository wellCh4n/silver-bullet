package com.github.wellch4n.silver.bullet.database.elasticsearch;

import com.github.wellch4n.silver.bullet.database.api.Database;
import com.github.wellch4n.silver.bullet.database.api.DatabaseOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactory;

import javax.persistence.EntityTransaction;
import java.util.concurrent.Callable;

/**
 * @author wellCh4n
 * @date 2023/9/12
 */
public class ElasticSearchDatabase extends Database {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public ElasticSearchDatabase(DatabaseOptions options) {
        super(options);
        ElasticSearchDatabaseOptions elasticSearchDatabaseOptions = (ElasticSearchDatabaseOptions) options;


        RestClientBuilder restClientBuilder = RestClient.builder(elasticSearchDatabaseOptions.getHosts());
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);

        this.elasticsearchRestTemplate = new ElasticsearchRestTemplate(restHighLevelClient);
    }

    @Override
    public <T> T getRepository(Class<T> repositoryClazz) {
        ElasticsearchRepositoryFactory factory = new ElasticsearchRepositoryFactory(elasticsearchRestTemplate);
        return factory.getRepository(repositoryClazz);
    }

    @Override
    public <T> T executeTx(Callable<T> callable) {
        throw new UnsupportedOperationException();
    }
}
