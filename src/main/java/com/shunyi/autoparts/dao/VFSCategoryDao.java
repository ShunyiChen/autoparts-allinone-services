package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.VFSCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/** VFS类目找不到异常 */
public interface VFSCategoryDao extends JpaRepository<VFSCategory, Long> {
}
