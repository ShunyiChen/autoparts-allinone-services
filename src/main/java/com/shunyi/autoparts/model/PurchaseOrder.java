package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 进货单实体类
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "purchase_orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 单号 */
    private String orderId;
    /** 单据日期 */
    private Date orderDate;
    /** 仓库 */
    @ManyToOne
    @JoinColumn(name = "order_warehouse_id",
            foreignKey = @ForeignKey(name = "ORDER_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
//    /** 打包及其明细 */
//    @OneToOne
//    @JoinColumn(name = "package_id",
//            foreignKey = @ForeignKey(name = "PACKAGE_ID_FK")
//    )
//    private Package aPackage;
    /** 供应商 */
    @ManyToOne
    @JoinColumn(name = "supplier_id",
            foreignKey = @ForeignKey(name = "SUPPLIER_ID_FK")
    )
    private Supplier supplier;
    /** 发票类型 */
    private String invoiceType;
    /** 发票号 */
    private String invoiceNo;
    /** 运费 */
    private BigDecimal freight;
    /** 备注 */
    private String notes;
    /** 操作用户 */
    @ManyToOne
    @JoinColumn(name = "operator_id",
            foreignKey = @ForeignKey(name = "OPERATOR_ID_FK")
    )
    private User operator;
    /** 经办人 */
    @ManyToOne
    @JoinColumn(name = "verifier_id",
            foreignKey = @ForeignKey(name = "VERIFIER_ID_FK")
    )
    private User verifier;
    /** 结算方式 */
    @OneToOne
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;
    /** 货款金额 */
    private BigDecimal amountA;
    /** 代垫费用 */
    private BigDecimal amountB;
    /** 本次优惠 */
    private BigDecimal amountC;
    /** 应付总额 */
    private BigDecimal amountD;
    /** 本次付款 */
    private BigDecimal amountE;
    /** 账号 */
    private String account;
    /** 订单状态 */
    private String status;
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
