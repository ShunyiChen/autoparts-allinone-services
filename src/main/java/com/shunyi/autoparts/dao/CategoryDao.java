package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description 产品类目Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface CategoryDao extends JpaRepository<Category, Long> {

    List<Category> findAllByStoreIdOrderByIdAsc(Long storeId);

    Category findByParentIdAndStoreId(Long parentId, Long storeId);
}
