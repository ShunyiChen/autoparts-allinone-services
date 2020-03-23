package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.UserShopMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 用户店铺映射关系Dao */
@Repository
public interface UserShopMappingDao extends JpaRepository<UserShopMapping, UserShopMapping.Id> {

    List<UserShopMapping> findAllByUserIdOrderByShopIdAsc(Long userId);

    List<UserShopMapping> findAllByShopIdOrderByUserIdAsc(Long shopId);
}