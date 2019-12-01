package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/** 权限Dao */
public interface PermissionDao extends JpaRepository<Permission, Long> {
}
