package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/** 部门Dao */
public interface DepartmentDao extends JpaRepository<Department, Long> {
}
