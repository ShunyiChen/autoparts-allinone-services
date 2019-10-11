package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/** 公司Dao */
public interface CompanyDao extends JpaRepository<Company, Long> {
}
