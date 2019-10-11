package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.VFS;
import org.springframework.data.jpa.repository.JpaRepository;

/** VFS(虚拟文件系统)Dao */
public interface VFSDao extends JpaRepository<VFS, Long> {
}
