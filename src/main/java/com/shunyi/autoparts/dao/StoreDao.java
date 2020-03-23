package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 店铺Dao */
public interface ShopDao extends JpaRepository<Shop, Long> {
}
