package com.github.wellch4n.silver.bullet.test;

import com.github.wellch4n.silver.bullet.database.api.Database;
import com.github.wellch4n.silver.bullet.database.api.DatabaseFactory;
import com.github.wellch4n.silver.bullet.database.elasticsearch.ElasticSearchDatabaseOptions;
import com.github.wellch4n.silver.bullet.database.jdbc.JDBCDatabaseOptions;
import com.github.wellch4n.silver.bullet.json.api.JSON;
import com.github.wellch4n.silver.bullet.test.es.repository.PDFInfoRepository;
import com.github.wellch4n.silver.bullet.test.jdbc.model.Test;
import com.github.wellch4n.silver.bullet.test.jdbc.repository.Test2Repository;
import com.github.wellch4n.silver.bullet.test.jdbc.repository.TestRepository;
import org.apache.http.HttpHost;

/**
 * @author wellCh4n
 * @date 2023/9/11
 */
public class DatabaseTest {

    @org.junit.jupiter.api.Test
    public void testJDBC() {
        JDBCDatabaseOptions jdbcDatabaseOptions = new JDBCDatabaseOptions();
        jdbcDatabaseOptions.setDriver("com.mysql.cj.jdbc.Driver");
        jdbcDatabaseOptions.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        jdbcDatabaseOptions.setUsername("root");
        jdbcDatabaseOptions.setPassword("well1995");
        jdbcDatabaseOptions.setEntityPackage("com.github.wellch4n.silver.bullet.test.jdbc.model");
        jdbcDatabaseOptions.setRepositories(TestRepository.class, Test2Repository.class);

        Database database = DatabaseFactory.build(jdbcDatabaseOptions);

        TestRepository repository = database.getRepository(TestRepository.class);
        System.out.println(JSON.toString(repository.findAll()));
        System.out.println(JSON.toString(repository.findByName("hello")));

        Test2Repository test2Repository = database.getRepository(Test2Repository.class);
        System.out.println(JSON.toString(test2Repository.findAll()));

        Test result = database.executeTx(() -> {
            Test test = new Test();
            test.setName("wellCh4n");
            repository.save(test);
            return test;
        });

        System.out.println(JSON.toString(result));
    }

    @org.junit.jupiter.api.Test
    public void testES() {
        ElasticSearchDatabaseOptions elasticSearchDatabaseOptions = new ElasticSearchDatabaseOptions();
        elasticSearchDatabaseOptions.setHosts(new HttpHost[]{new HttpHost("10.0.2.57", 30920)});
        elasticSearchDatabaseOptions.setRepositories(PDFInfoRepository.class);

        Database database = DatabaseFactory.build(elasticSearchDatabaseOptions);

        PDFInfoRepository repository = database.getRepository(PDFInfoRepository.class);
        System.out.println(JSON.toString(repository.findAll()));
        System.out.println(JSON.toString(repository.findById("qfkakIkBgtC14LFco0-Y").orElse(null)));
        System.out.println(JSON.toString(repository.findAllByContent("")));
    }
}
