package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description 店铺Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface StoreDao extends JpaRepository<Store, Long> {

    List<Store> findAllByNameOrderByIdAsc(String name);
}
