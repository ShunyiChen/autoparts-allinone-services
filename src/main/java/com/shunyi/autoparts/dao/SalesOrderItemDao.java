package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 销售单明细Dao
 * @author Shunyi
 * @date 2020/5/12
 */
public interface SalesOrderItemDao extends JpaRepository<SalesOrderItem, Long>, JpaSpecificationExecutor<SalesOrderItem> {

    List<SalesOrderItem> findAllBySalesOrderIdOrderByIdAsc(Long salesOrderId);
}
