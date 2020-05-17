package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PriceAdjustmentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 调价单Dao
 * @author Shunyi
 * @date 2020/5/15
 */
public interface PriceAdjustmentOrderDao extends JpaRepository<PriceAdjustmentOrder, Long> , JpaSpecificationExecutor<PriceAdjustmentOrder> {

    /** 查询 */
    List<PriceAdjustmentOrder> findAllByOrderNoOrderByIdAsc(String orderNo);
}
