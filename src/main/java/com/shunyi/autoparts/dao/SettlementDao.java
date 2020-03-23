package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 结算方式Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface SettlementDao extends JpaRepository<Settlement, Long> {
}
