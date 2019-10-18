package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** 登录用户Dao */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);
}