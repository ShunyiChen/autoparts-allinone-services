package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 角色Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface RoleDao extends JpaRepository<Role, Long> {
}
