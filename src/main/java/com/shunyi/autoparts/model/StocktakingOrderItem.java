package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 盘点单明细实体类
 * @Author: Shunyi
 * @CreateDate: 2020/5/14
 */
@Entity
@Table(name = "stocktaking_orders_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StocktakingOrderItem {
    /** ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 盘点单 */
    @ManyToOne
    @JoinColumn(name = "stocktaking_order_id",
            foreignKey = @ForeignKey(name = "STOCKTAKING_ORDER_ID")
    )
    private StocktakingOrder stocktakingOrder;
    /** SKU */
    @ManyToOne
    @JoinColumn(name = "stocktaking_order_items_sku_id",
            foreignKey = @ForeignKey(name = "STOCKTAKING_ORDER_ITEMS_SKU_ID")
    )
    private SKU sku;
    /** 点货数量 */
    private Integer stocktakingQty;
    /** 点货时间 */
    private Date stocktakingDate;
    /** 备注 */
    private String notes;
}
