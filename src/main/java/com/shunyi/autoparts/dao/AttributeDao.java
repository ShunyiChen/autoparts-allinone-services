package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 产品基本属性Dao */
public interface AttributeDao extends JpaRepository<Attribute, Long> {

    List<Attribute> findByProduct_id(Long product_id);

    /** 删除属性按照产品ID和属性值ID */
    List<Attribute> findByProduct_idAndAttributeValueId(Long product_id, Long attributeValueId);
}
