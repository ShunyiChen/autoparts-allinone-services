package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 结算方式 */
@Entity
@Table(name = "settlements")
public class Settlement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /** 结算方式 */
    private String name;

    public Settlement() {}

    public Settlement(String name) {
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
        return "Settlement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
