package com.github.wellch4n.silver.bullet.database.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wellCh4n
 * @date 2023/9/11
 */
public abstract class DatabaseOptions {

    public abstract Class<? extends Database> clazz();

    private String entityPackage;
    private Set<Class<? extends Repository<?, ?>>> repositories;

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public Set<Class<? extends Repository<?, ?>>> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<Class<? extends Repository<?, ?>>> repositories) {
        this.repositories = repositories;
    }

    @SafeVarargs
    public final void setRepositories(Class<? extends Repository<?, ?>>... repositories) {
        this.repositories = Arrays.stream(repositories).collect(Collectors.toSet());
    }
}
