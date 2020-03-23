package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.AttributeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 属性名Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface AttributeNameDao extends JpaRepository<AttributeName, Long>, JpaSpecificationExecutor<AttributeName> {

     List<AttributeName> findAllByCategory_idOrderByIdAsc(Long Category_id);
}
