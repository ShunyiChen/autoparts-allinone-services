package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Import;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 配件进口Dao
 * @author Shunyi Chen
 * @date 2020/4/11
 */
public interface ImportDao extends JpaRepository<Import, Long>  {
}
