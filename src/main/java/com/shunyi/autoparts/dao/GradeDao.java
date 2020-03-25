package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 信誉等级Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface GradeDao extends JpaRepository<Grade, Long> {
}
