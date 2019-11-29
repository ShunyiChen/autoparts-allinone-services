package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

/** 店铺Dao */
public interface ShopDao extends JpaRepository<Shop, Long> {
}
