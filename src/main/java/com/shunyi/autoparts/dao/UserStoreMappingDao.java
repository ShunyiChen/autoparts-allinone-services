package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.UserStoreMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 用户与店铺关系Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Repository
public interface UserStoreMappingDao extends JpaRepository<UserStoreMapping, UserStoreMapping.Id> {

    List<UserStoreMapping> findAllByUserIdOrderByShopIdAsc(Long userId);

    List<UserStoreMapping> findAllByShopIdOrderByUserIdAsc(Long shopId);
}