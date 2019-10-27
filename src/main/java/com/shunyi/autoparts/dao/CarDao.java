package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/** 车型类目Dao */
public interface CarDao extends JpaRepository<Car, Long> {
}
