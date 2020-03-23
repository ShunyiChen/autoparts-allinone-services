package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SKUSlotMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description SKU与货位映射关系Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface SKUSlotDao extends JpaRepository<SKUSlotMapping, SKUSlotMapping.Id> {
}
