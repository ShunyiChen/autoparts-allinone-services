package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 销售单
 * @Author: Shunyi
 * @CreateDate: 2020/5/11 23:09
 */
@Entity
@Table(name = "sales_orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SalesOrder {
    /** ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 业务单号 */
    private String orderNo;
    /** 单据日期 */
    private Date orderDate;
    /** 仓库 */
    @ManyToOne
    @JoinColumn(name = "sales_warehouse_id",
            foreignKey = @ForeignKey(name = "SALES_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
    /** 客户 */
    @ManyToOne
    @JoinColumn(name = "sales_consumer_id",
            foreignKey = @ForeignKey(name = "SALES_CONSUMER_ID_FK")
    )
    private Consumer consumer;
    /** 发票类型 */
    private String invoiceType;
    /** 发票号 */
    private String invoiceNo;
    /** 运费 */
    private BigDecimal freight;
    /** 备注 */
    private String notes;
    /** 经办人 */
    private String operator;
    /** 系统登录账号 */
    private String userName;
    /** 结算方式 */
    private String payment;
    /** 进货数量 */
    private Integer purchaseQty;
    /** 已入库数量 */
    private Integer warehouseQty;
    /** 退货数量合计 */
    private Integer returnedTotalQty;
    /** 货款金额 */
    private BigDecimal purchaseAmount;
    /** 垫付费用 */
    private BigDecimal disbursementAmount;
    /** 本次优惠 */
    private BigDecimal discountAmount;
    /** 应收总额 */
    private BigDecimal amountReceivable;
    /** 本次收款 */
    private BigDecimal payeeAmount;
    /** 账号 */
    private String account;
    /** 还款金额 */
    private BigDecimal repaymentAmount;
    /** 还款日期 */
    private Date repaymentDate;
    /** 订单状态 */
    private String status;
    /** 创建时间 */
    private Date dateCreated;
    /** 创建者 */
    private String creator;
    /** 删除时间 */
    private Date dateDeleted;
    /** 删除标记 */
    private Boolean deleteFlag;
    /** 删除者 */
    private String deleter;

    /** 搜索日期类型 */
    @Transient
    private String dateType;
    @Transient
    /** 起始日期 */
    private Date fromDate;
    @Transient
    /** 结束日期 */
    private Date toDate;
}
