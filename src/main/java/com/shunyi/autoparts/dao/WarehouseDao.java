package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description 仓库Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface WarehouseDao extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findAllByOrderByIdAsc();

    List<Warehouse> findAllByNameOrderByIdAsc(String name);
}
