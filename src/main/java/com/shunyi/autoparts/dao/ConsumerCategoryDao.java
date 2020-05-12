package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.ConsumerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description 客户分类Dao
 * @author Shunyi
 * @date 2020/5/12
 */
public interface ConsumerCategoryDao extends JpaRepository<ConsumerCategory, Long> {

    List<ConsumerCategory> findAllByOrderByIdAsc();

    /** 取根节点 */
    ConsumerCategory findByParentId(Long parentId);
}
