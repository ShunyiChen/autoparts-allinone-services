package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PriceAdjustmentOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description: 调价单明细Dao
 * @Author: Shunyi
 * @CreateDate: 2020/5/15
 */
public interface PriceAdjustmentOrderItemDao extends JpaRepository<PriceAdjustmentOrderItem, Long>, JpaSpecificationExecutor<PriceAdjustmentOrderItem> {

    List<PriceAdjustmentOrderItem> findAllByPriceAdjustmentOrderIdOrderByIdAsc(Long priceAdjustmentOrderId);
}
