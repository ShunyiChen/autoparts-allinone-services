package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SKUCargoSpaceMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/** SKU与货位映射关系Dao */
public interface SKUCargoSpaceDao extends JpaRepository<SKUCargoSpaceMapping, SKUCargoSpaceMapping.Id> {
}
