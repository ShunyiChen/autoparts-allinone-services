package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Logo;
import org.springframework.data.jpa.repository.JpaRepository;

/** 产品Logo Dao */
public interface LogoDao extends JpaRepository<Logo, Long> {
}
