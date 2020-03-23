package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 属性值Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface AttributeValueDao extends JpaRepository<AttributeValue, Long>, JpaSpecificationExecutor<AttributeValue> {

    /** 正序查询属性值列表按照产品类目ID */
    List<AttributeValue> findAllByCategory_idOrderByIdAsc(Long Category_id);

    /** 正序查询属性值列表通过属性名ID */
    List<AttributeValue> findAllByAttributeName_idOrderByIdAsc(Long attributeName_id);
}
