package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description 货位Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface SlotDao extends JpaRepository<Slot, Long>, JpaSpecificationExecutor<Slot> {
}
