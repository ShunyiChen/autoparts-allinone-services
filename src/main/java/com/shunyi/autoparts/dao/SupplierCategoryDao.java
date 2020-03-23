package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SupplierCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description 供应商类目Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface SupplierCategoryDao extends JpaRepository<SupplierCategory, Long> {

    List<SupplierCategory> findAllByOrderByIdAsc();
}
