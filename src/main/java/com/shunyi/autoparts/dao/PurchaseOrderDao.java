package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description 采购订单Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Long>, JpaSpecificationExecutor<PurchaseOrder> {
}
