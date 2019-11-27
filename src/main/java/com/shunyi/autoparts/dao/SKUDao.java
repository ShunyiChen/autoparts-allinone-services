package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/** 产品SKU Dao */
public interface SKUDao extends JpaRepository<SKU, Long>, JpaSpecificationExecutor<SKU> {

    List<SKU> findAllByProduct_idOrderByIdAsc(Long Product_id);

//    SKU findByProduct_idAndPropertiesOrderByIdAsc(Long Product_id, String properties);
}
