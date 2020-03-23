package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SKUCargoSpaceMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description SKU与货位映射关系Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface SKUSlotDao extends JpaRepository<SKUCargoSpaceMapping, SKUCargoSpaceMapping.Id> {
}
