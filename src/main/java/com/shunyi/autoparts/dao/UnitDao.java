package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 配件单位Dao
 * @author Shunyi Chen
 * @date 2020/4/7
 */
public interface UnitDao extends JpaRepository<Unit, Long>  {
}
