package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 所属公司Dao
 * @author Shunyi Chen
 * @date 2020/4/11
 */
public interface CompanyDao extends JpaRepository<Company, Long>  {
}
