package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.BasicAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description 基本属性Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface BasicAttributeDao extends JpaRepository<BasicAttributes, Long> {

    List<BasicAttributes> findByProduct_id(Long Product_id);

    /** 按产品ID和属性名ID删除 */
    List<BasicAttributes> findByProduct_idAndAttributeNameId(Long Product_id, Long AttributeNameId);
}
