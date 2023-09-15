package com.github.wellch4n.silver.bullet.test.jdbc.repository;

import com.github.wellch4n.silver.bullet.test.jdbc.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wellCh4n
 * @date 2023/9/11
 */
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByName(String name);
}
