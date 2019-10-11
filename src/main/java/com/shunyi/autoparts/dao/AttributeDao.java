package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

/** 产品基本属性Dao */
public interface AttributeDao extends JpaRepository<Attribute, Long> {
}
