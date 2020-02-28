package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.BasicAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 产品基本属性Dao */
public interface BasicAttributeDao extends JpaRepository<BasicAttributes, Long> {

    List<BasicAttributes> findByProduct_id(Long Product_id);

    /** 按产品ID和属性名ID删除 */
    List<BasicAttributes> findByProduct_idAndAttributeNameId(Long Product_id, Long AttributeNameId);
}
