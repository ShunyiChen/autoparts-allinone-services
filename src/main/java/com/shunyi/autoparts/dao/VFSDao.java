package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.VFS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** VFS(虚拟文件系统)Dao */
public interface VFSDao extends JpaRepository<VFS, Long> {

    List<VFS> findAllByCategoryIdOrderById(Long categoryId);
}
