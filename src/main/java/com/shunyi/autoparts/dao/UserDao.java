package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 登录用户Dao */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

//	List<User> findAllByShop_idOrderById(Long shop_id);
}