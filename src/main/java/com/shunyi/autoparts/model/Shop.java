package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** 店铺 */
@Entity
@Table(name = "shops")
public class Shop {
    /** ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /** 名称 */
    private String name;

    /** 用户店铺映射关系 */
    @OneToMany(mappedBy = "shop")
    protected Set<UserShopMapping> userShopMappingSet = new HashSet<>();

    /** 公司 */
    @ManyToOne
    @JoinColumn(name = "company_id",
            foreignKey = @ForeignKey(name = "COMPANY_ID_FK")
    )
    private Company company;

    public Shop() {}

    public Shop(String name, Set<UserShopMapping> userShopMappingSet, Company company) {
        this.name = name;
        this.userShopMappingSet = userShopMappingSet;
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

    public Set<UserShopMapping> getUserShopMappingSet() {
        return userShopMappingSet;
    }

    public void setUserShopMappingSet(Set<UserShopMapping> userShopMappingSet) {
        this.userShopMappingSet = userShopMappingSet;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
