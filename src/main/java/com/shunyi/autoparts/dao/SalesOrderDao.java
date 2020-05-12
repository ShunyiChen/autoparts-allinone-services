package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 销售单Dao
 * @author Shunyi
 * @date 2020/5/12
 */
public interface SalesOrderDao extends JpaRepository<SalesOrder, Long>, JpaSpecificationExecutor<SalesOrder> {

    /** 查询 */
    List<SalesOrder> findAllByOrderNoOrderByIdAsc(String orderNo);
}
