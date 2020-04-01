package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description: 采购订单明细Dao
 * @Author: 陈顺谊
 * @CreateDate: 2020/4/1 13:07
 * @Version: 1.0
 */
public interface PurchaseOrderItemDao extends JpaRepository<PurchaseOrderItem, Long>, JpaSpecificationExecutor<PurchaseOrderItem> {

    List<PurchaseOrderItem> findAllByPurchaseOrderIdOrderByIdAsc(Long purchaseOrderId);
}
