package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 用户和角色映射关系Dao */
@Repository
public interface UserRoleMappingDao extends JpaRepository<UserRoleMapping, UserRoleMapping.Id> {

    List<UserRoleMapping> findAllByUserIdOrderByRoleIdAsc(Long userId);

    List<UserRoleMapping> findAllByRoleIdOrderByUserIdAsc(Long roleId);
}