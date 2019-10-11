package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

/** 属性值Dao */
public interface AttributeValueDao extends JpaRepository<AttributeValue, Long> {
}
