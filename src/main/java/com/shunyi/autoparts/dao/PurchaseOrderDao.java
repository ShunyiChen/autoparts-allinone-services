package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 进货单Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Long> {
}
