package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.CargoSpace;
import org.springframework.data.jpa.repository.JpaRepository;

/** 货位Dao */
public interface CargoSpaceDao extends JpaRepository<CargoSpace, Long> {
}
