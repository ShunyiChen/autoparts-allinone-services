package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

/** 打包品Dao */
public interface PackageDao extends JpaRepository<Package, Long> {
}
