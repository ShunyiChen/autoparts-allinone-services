package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PurchaseReturnOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description: 采购退货单明细Dao
 * @Author: Shunyi
 * @CreateDate: 2020/5/9
 */
public interface PurchaseReturnOrderItemDao extends JpaRepository<PurchaseReturnOrderItem, Long>, JpaSpecificationExecutor<PurchaseReturnOrderItem> {

    List<PurchaseReturnOrderItem> findAllByPurchaseReturnOrderIdOrderByIdAsc(Long purchaseReturnOrderId);
}
