package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 产品基本属性Dao */
public interface AttributeDao extends JpaRepository<Attribute, Long> {

//    List<Attribute> findAllByProduct_idAndAttributeName_id(Long product_id, Long attributeName_id);
}
