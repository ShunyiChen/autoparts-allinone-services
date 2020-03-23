package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Logo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description Logo Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface LogoDao extends JpaRepository<Logo, Long> {
}
