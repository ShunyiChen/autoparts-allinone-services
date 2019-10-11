package com.shunyi.autoparts.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shunyi.autoparts.model.User;

/** 登录用户Dao */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);
}