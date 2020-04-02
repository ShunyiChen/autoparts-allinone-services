package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 车型Dao
 * @author Shunyi Chen
 * @date 2020/4/2
 */
public interface CarDao extends JpaRepository<Car, Long> {
}
