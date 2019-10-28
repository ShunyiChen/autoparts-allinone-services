package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 产品Dao */
public interface ProductDao extends JpaRepository<Product, Long> {

    List<Product> findAllByBrandSeries_idOrderByIdAsc(Long BrandSeries_id);
}
