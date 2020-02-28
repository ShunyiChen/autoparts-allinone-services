package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.BasicAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 产品基本属性Dao */
public interface AttributeDao extends JpaRepository<BasicAttribute, Long> {

    List<BasicAttribute> findByProduct_id(Long product_id);

    /** 删除属性按照产品ID和属性值ID */
    List<BasicAttribute> findByProduct_idAndAttributeValueId(Long product_id, Long attributeValueId);
}
