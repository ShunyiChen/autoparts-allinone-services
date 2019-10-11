package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/** 产品Dao */
public interface ProductDao extends JpaRepository<Product, Long> {
}
