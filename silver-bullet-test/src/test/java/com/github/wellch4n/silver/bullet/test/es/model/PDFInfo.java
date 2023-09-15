package com.github.wellch4n.silver.bullet.test.es.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author wellCh4n
 * @date 2023/9/12
 */

@Entity
@Document(indexName = "pdf_info")
public class PDFInfo {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String content;

    @Field(type = FieldType.Text)
    private String documentArchivalNumber;

    @Field(type = FieldType.Long)
    private Long documentId;

    @Field(type = FieldType.Text)
    private String documentTitle;

    @Field(type = FieldType.Text)
    private String volumeTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDocumentArchivalNumber() {
        return documentArchivalNumber;
    }

    public void setDocumentArchivalNumber(String documentArchivalNumber) {
        this.documentArchivalNumber = documentArchivalNumber;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getVolumeTitle() {
        return volumeTitle;
    }

    public void setVolumeTitle(String volumeTitle) {
        this.volumeTitle = volumeTitle;
    }
}
