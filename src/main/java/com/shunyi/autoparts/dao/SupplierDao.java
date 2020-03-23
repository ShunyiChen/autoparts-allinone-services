package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description 供应商Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface SupplierDao extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {
}
