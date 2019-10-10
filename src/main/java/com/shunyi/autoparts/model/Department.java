package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 部门 */
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 名称 */
    private String name;
    /** 公司 */
    @ManyToOne
    @JoinColumn(name = "company_id",
            foreignKey = @ForeignKey(name = "COMPANY_ID_FK")
    )
    private Company company;

    public Department() {}

    public Department(String name, Company company) {
        this.name = name;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
