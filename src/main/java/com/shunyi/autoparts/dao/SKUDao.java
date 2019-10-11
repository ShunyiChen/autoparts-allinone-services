package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SKU;
import org.springframework.data.jpa.repository.JpaRepository;

/** 产品SKU Dao */
public interface SKUDao extends JpaRepository<SKU, Long> {
}
