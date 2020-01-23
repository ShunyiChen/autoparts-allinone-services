package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.VFS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/** VFS(虚拟文件系统)Dao */
public interface VFSDao extends JpaRepository<VFS, Long> {

    List<VFS> findAllByCategoryIdOrderById(Long categoryId);

    @Modifying
    @Transactional
    @Query("update VFS v set v.acquiescent=false")
    void restoreAcquiescent();

    @Modifying
    @Transactional
    @Query("update VFS v set v.acquiescent=true where v.id=:vfsId")
    void updateAcquiescentByVFSId(@Param(value = "vfsId") Long vfsId);
}
