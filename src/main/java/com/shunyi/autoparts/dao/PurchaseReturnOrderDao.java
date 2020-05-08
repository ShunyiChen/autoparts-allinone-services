package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PurchaseReturnOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description 采购退货单Dao
 * @author Shunyi Chen
 * @date 2020/5/9
 */
public interface PurchaseReturnOrderDao extends JpaRepository<PurchaseReturnOrder, Long>, JpaSpecificationExecutor<PurchaseReturnOrder> {
}
