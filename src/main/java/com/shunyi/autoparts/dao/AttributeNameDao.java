package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.AttributeName;
import org.springframework.data.jpa.repository.JpaRepository;

/** 属性名Dao */
public interface AttributeNameDao extends JpaRepository<AttributeName, Long> {
}
