package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description: 价格调整单明细实体类
 * @Author: Shunyi
 * @CreateDate: 2020/5/15
 */
@Entity
@Table(name = "price_adjustment_order_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PriceAdjustmentOrderItem {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 调价单 */
    @ManyToOne
    @JoinColumn(name = "price_adjustment_order_id",
            foreignKey = @ForeignKey(name = "PRICE_ADJUSTMENT_ORDER_ID_FK")
    )
    private PriceAdjustmentOrder priceAdjustmentOrder;
    /** SKU */
    @ManyToOne
    @JoinColumn(name = "price_adjustment_order_items_sku_id",
            foreignKey = @ForeignKey(name = "PRICE_ADJUSTMENT_ORDER_ITEMS_SKU_ID")
    )
    private SKU sku;
    /** 调整价 */
    private BigDecimal priceOfAdjustment;
    /** 备注 */
    private String notes;
}
