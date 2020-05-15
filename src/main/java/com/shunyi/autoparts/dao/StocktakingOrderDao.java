package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.StocktakingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description 盘点单Dao
 * @author Shunyi
 * @date 2020/5/14
 */
public interface StocktakingOrderDao extends JpaRepository<StocktakingOrder, Long>, JpaSpecificationExecutor<StocktakingOrder> {
}
