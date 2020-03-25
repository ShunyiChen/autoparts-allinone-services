package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 供应商
 * @author Shunyi Chen
 * @date 2020/3/23
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
    /** 供应商单位名称 */
    private String name;
    /** 联系人 */
    private String contact;
    /** 电话1 */
    private String phone1;
    /** 电话2 */
    private String phone2;
    /** 电子邮件 */
    private String email;
    /** 地址 */
    private String address;
    /** 邮编 */
    private String postCode;
    /** 信誉等级 */
    @ManyToOne
    @JoinColumn(name = "supplier_grade_id",
            foreignKey = @ForeignKey(name = "SUPPLIER_GRADE_ID_FK")
    )
    private Grade grade;
    /** 付款方式 */
    @ManyToOne
    @JoinColumn(name = "supplier_payment_id",
            foreignKey = @ForeignKey(name = "SUPPLIER_PAYMENT_ID_FK")
    )
    private Payment payment;
    /** 备注 */
    private String notes;
    /** 供应商类目 */
    @ManyToOne
    @JoinColumn(name = "supplier_category_id",
            foreignKey = @ForeignKey(name = "SUPPLIER_CATEGORY_ID_FK")
    )
    private SupplierCategory category;
    /** 创建时间 */
    private Date dateCreated;
    /** 创建者 */
    private String creator;
    /** 更新时间 */
    private Date dateUpdated;
    /** 更新者 */
    private String updater;
    /** 更新次数 */
    private Integer updatedCount;
    /** 删除时间 */
    private Date dateDeleted;
    /** 删除标记 */
    private Boolean deleteFlag;
    /** 删除者 */
    private String deleter;
}
