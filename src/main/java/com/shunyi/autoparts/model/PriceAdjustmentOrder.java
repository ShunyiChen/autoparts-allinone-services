package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 价格调整单实体类
 * @Author: Shunyi
 * @CreateDate: 2020/5/15
 */
@Entity
@Table(name = "price_adjustment_orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PriceAdjustmentOrder {
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
    @JoinColumn(name = "price_adjustment_order_warehouse_id",
            foreignKey = @ForeignKey(name = "PRICE_ADJUSTMENT_ORDER_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
    /** 调价金额 */
    private BigDecimal amountOfAdjustment;
    /** 经办人 */
    private String operator;
    /** 操作员 */
    private String userName;
    /** 备注 */
    private String notes;
    /** 订单状态 */
    private String status;
    /** 创建时间 */
    private Date dateCreated;
    /** 创建者 */
    private String creator;
    /** 起始日期 */
    @Transient
    private Date fromDate;
    /** 结束日期 */
    @Transient
    private Date toDate;
}
