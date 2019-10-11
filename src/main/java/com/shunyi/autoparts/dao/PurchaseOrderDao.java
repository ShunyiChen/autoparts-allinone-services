package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/** 进货单Dao */
public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Long> {
}
