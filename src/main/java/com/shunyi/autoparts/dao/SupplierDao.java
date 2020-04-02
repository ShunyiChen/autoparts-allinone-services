package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 供应商Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface SupplierDao extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {

    /**
     * 获取Supplier列表通过分类ID
     *
     * @param supplierCategoryId
     * @return
     */
    List<Supplier> findAllBySupplierCategoryIdOrderByIdAsc(Long supplierCategoryId);
}
