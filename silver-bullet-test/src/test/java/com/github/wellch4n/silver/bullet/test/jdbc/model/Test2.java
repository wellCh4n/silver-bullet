package com.github.wellch4n.silver.bullet.test.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wellCh4n
 * @date 2023/9/12
 */

@Entity
@Table(name = "test2")
public class Test2 {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "a")
    private String a;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
