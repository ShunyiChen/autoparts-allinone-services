package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Stocktaking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 盘点方式Dao
 * @author Shunyi
 * @date 2020/5/14
 */
public interface StocktakingDao extends JpaRepository<Stocktaking, Long> {
}
