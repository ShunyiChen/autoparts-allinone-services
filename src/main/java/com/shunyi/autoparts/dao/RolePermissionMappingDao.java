package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.RolePermissionMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 角色和权限映射关系Dao */
@Repository
public interface RolePermissionMappingDao extends JpaRepository<RolePermissionMapping, RolePermissionMapping.Id> {

    List<RolePermissionMapping> findAllByRoleIdOrderByPermissionIdAsc(Long roleId);

    List<RolePermissionMapping> findAllByPermissionIdOrderByRoleIdAsc(Long permissionId);
}