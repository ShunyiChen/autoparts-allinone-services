package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 仓库Dao */
public interface WarehouseDao extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findAllByOrderByIdAsc();
}
