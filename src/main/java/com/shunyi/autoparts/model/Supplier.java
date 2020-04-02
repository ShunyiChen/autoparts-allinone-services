package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 供应商实体类
 * @author Shunyi Chen
 * @date 2020/4/2
 */
@Entity
@Table(name = "suppliers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Supplier {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 供应商编码 */
    private String code;
    /** 供应商类目 */
    @ManyToOne
    @JoinColumn(name = "supplier_category_id",
            foreignKey = @ForeignKey(name = "SUPPLIER_CATEGORY_ID_FK")
    )
    private SupplierCategory supplierCategory;
    /** 供应商单位名称 */
    private String name;
    /** 省市 */
    private String city;
    /** 联系人 */
    private String contact;
    /** 电话 */
    private String phone;
    /** 电子邮件 */
    private String email;
    /** 地址 */
    private String address;
    /** 邮编 */
    private String postCode;
    /** 网站 */
    private String webSite;
    /** 全程 */
    private String fullName;
    /** 法人 */
    private String corp;
    /** 银行 */
    private String bank;
    /** 开户账号 */
    private String account;
    /** 税号 */
    private String taxFileNumber;
    /** 开票地址 */
    private String billingAddress;
    /** 备注 */
    private String notes;
}
