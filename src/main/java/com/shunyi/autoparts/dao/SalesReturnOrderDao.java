package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SalesReturnOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 销售退货单Dao
 * @author Shunyi
 * @date 2020/5/13
 */
public interface SalesReturnOrderDao extends JpaRepository<SalesReturnOrder, Long>, JpaSpecificationExecutor<SalesReturnOrder> {

    /** 查询 */
    List<SalesReturnOrder> findAllByOrderNoOrderByIdAsc(String orderNo);
}
