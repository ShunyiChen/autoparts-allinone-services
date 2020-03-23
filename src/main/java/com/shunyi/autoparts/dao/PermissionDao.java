package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 权限Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface PermissionDao extends JpaRepository<Permission, Long> {
}
