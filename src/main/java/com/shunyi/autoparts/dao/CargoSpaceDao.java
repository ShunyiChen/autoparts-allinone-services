package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.CargoSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/** 货位Dao */
public interface CargoSpaceDao extends JpaRepository<CargoSpace, Long>, JpaSpecificationExecutor<CargoSpace> {
}
