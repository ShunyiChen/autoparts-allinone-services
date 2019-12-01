package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/** 角色Dao */
public interface RoleDao extends JpaRepository<Role, Long> {
}
