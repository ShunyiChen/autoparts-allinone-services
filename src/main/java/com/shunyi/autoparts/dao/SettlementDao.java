package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

/** 结算方式Dao */
public interface SettlementDao extends JpaRepository<Settlement, Long> {
}
