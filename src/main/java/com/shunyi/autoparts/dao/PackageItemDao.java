package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.PackageItem;
import org.springframework.data.jpa.repository.JpaRepository;

/** 打包品余项Dao */
public interface PackageItemDao extends JpaRepository<PackageItem, Long> {
}
