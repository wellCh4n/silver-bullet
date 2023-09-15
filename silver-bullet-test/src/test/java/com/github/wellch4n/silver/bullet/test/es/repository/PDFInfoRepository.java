package com.github.wellch4n.silver.bullet.test.es.repository;

import com.github.wellch4n.silver.bullet.test.es.model.PDFInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wellCh4n
 * @date 2023/9/12
 */
public interface PDFInfoRepository extends CrudRepository<PDFInfo, String> {
    List<PDFInfo> findAllByContent(String content);
}
