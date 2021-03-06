package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @description 系统用户Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

	@Modifying
	@Transactional
	@Query("update User u set u.password=:newPassword where u.id=:uid")
	void updatePasswordByUserId(@Param(value = "newPassword") String newPassword, @Param(value = "uid") Long uid);
}