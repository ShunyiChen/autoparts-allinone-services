package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.VFSCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description VFS类目Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface VFSCategoryDao extends JpaRepository<VFSCategory, Long> {
}
