package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.StocktakingOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 盘点单明细Dao
 * @author Shunyi
 * @date 2020/5/14
 */
public interface StocktakingOrderItemDao extends JpaRepository<StocktakingOrderItem, Long>, JpaSpecificationExecutor<StocktakingOrderItem> {

    List<StocktakingOrderItem> findAllByStocktakingOrderIdOrderByIdAsc(Long stocktakingOrderId);
}
