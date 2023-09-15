package com.github.wellch4n.silver.bullet.database.jdbc;

import com.github.wellch4n.silver.bullet.database.api.Database;
import com.github.wellch4n.silver.bullet.database.api.DatabaseOptions;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.concurrent.Callable;

/**
 * @author wellCh4n
 * @date 2023/9/11
 */
public class JDBCDatabase extends Database {

    private final EntityManagerFactory entityManagerFactory;
    private final ThreadLocal<EntityManager> entityManagerThreadLocal = new ThreadLocal<>();

    public JDBCDatabase(DatabaseOptions options) {
        super(options);
        JDBCDatabaseOptions jdbcDatabaseOptions = (JDBCDatabaseOptions) options;

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(jdbcDatabaseOptions.getDriver());
        dataSource.setUrl(jdbcDatabaseOptions.getUrl());
        dataSource.setUsername(jdbcDatabaseOptions.getUsername());
        dataSource.setPassword(jdbcDatabaseOptions.getPassword());

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan(jdbcDatabaseOptions.getEntityPackage());
        emf.afterPropertiesSet();

        this.entityManagerFactory = emf.getObject();
    }

    @Override
    public <T> T getRepository(Class<T> repositoryClazz) {
        EntityManager entityManager = getEntityManager();

        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(repositoryClazz);
    }

    @Override
    public <T> T executeTx(Callable<T> callable) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            T result = callable.call();
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    private EntityManager getEntityManager() {
        EntityManager entityManager = entityManagerThreadLocal.get();
        if (entityManager == null) {
            EntityManager newEntityManager = entityManagerFactory.createEntityManager();
            entityManagerThreadLocal.set(newEntityManager);
            return newEntityManager;
        }
        return entityManager;
    }
}
