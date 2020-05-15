package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 盘点单实体类
 * @Author: Shunyi
 * @CreateDate: 2020/5/14
 */
@Entity
@Table(name = "stocktaking_orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StocktakingOrder {
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
    @JoinColumn(name = "stocktaking_warehouse_id",
            foreignKey = @ForeignKey(name = "STOCKTAKING_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
    /** 盘点方式 */
    @ManyToOne
    @JoinColumn(name = "stocktaking_id",
            foreignKey = @ForeignKey(name = "STOCKTAKING_ID_FK")
    )
    private Stocktaking stocktaking;
    /** 盘点数量 */
    private Integer stocktakingQty;
    /** 盘点金额 */
    private BigDecimal stocktakingAmount;
    /** 经办人 */
    private String operator;
    /** 系统登录账号 */
    private String userName;
    /** 订单状态 */
    private String status;
    /** 备注 */
    private String notes;
    /** 起始日期 */
    @Transient
    private Date fromDate;
    /** 结束日期 */
    @Transient
    private Date toDate;
}
