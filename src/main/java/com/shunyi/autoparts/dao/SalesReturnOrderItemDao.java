package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SalesReturnOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description: 销售退货单明细Dao
 * @Author: Shunyi
 * @CreateDate: 2020/5/13
 */
public interface SalesReturnOrderItemDao extends JpaRepository<SalesReturnOrderItem, Long>, JpaSpecificationExecutor<SalesReturnOrderItem> {

    List<SalesReturnOrderItem> findAllBySalesReturnOrderIdOrderByIdAsc(Long salesReturnOrderId);
}
