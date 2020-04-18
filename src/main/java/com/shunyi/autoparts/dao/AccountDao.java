package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 进货单账号Dao
 * @author Shunyi Chen
 * @date 2020/4/18
 */
public interface AccountDao extends JpaRepository<Account, Long>  {
}
