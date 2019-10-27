package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 产品类目Dao */
public interface CategoryDao extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByIdAsc();
}
