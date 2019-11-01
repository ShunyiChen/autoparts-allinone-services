package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.AttributeName;
import com.shunyi.autoparts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/** 属性名Dao */
public interface AttributeNameDao extends JpaRepository<AttributeName, Long>, JpaSpecificationExecutor<AttributeName> {

     List<AttributeName> findAllByCategory_idOrderByIdAsc(Long Category_id);
}
