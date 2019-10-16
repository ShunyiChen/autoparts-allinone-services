package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/** 供应商Dao */
public interface SupplierDao extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {
}
