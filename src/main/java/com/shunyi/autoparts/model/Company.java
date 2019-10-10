package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 公司 */
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /** 名称 */
    private String name;

    public Company() {}

    public Company(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
