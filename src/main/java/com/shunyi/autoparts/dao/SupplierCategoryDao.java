package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SupplierCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 供应商类目Dao */
public interface SupplierCategoryDao extends JpaRepository<SupplierCategory, Long> {

    List<SupplierCategory> findAllByOrderByIdAsc();
}
