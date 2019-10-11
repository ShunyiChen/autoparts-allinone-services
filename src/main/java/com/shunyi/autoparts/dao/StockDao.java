package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/** 产品库存Dao */
public interface StockDao extends JpaRepository<Stock, Long> {
}
