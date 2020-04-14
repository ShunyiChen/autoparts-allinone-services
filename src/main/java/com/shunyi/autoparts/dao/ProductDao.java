package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 产品Dao
 * @author Shunyi Chen
 * @date 2020/4/14
 */
public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{

    /** 根据分类ID取产品ID */
    List<Product> findAllByCategoryIdOrderByIdAsc(Long categoryId);
}
