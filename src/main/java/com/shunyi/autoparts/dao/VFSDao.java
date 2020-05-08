package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.exception.VFSNotFoundException;
import com.shunyi.autoparts.model.VFS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @description VFS文件系统Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface VFSDao extends JpaRepository<VFS, Long> {

    /** 根据分类ID查找全部 */
    List<VFS> findAllByCategoryIdOrderById(Long categoryId);

    /**
     * 取消所有主设置
     */
    @Modifying
    @Transactional(rollbackOn = VFSNotFoundException.class)
    @Query("update VFS v set v.master=false")
    void restoreMaster();

    /**
     * 根据ID设置主要VFS
     * @param vfsId
     */
    @Modifying
    @Transactional(rollbackOn = VFSNotFoundException.class)
    @Query("update VFS v set v.master=true where v.id=:vfsId")
    void updateMasterByVFSId(@Param(value = "vfsId") Long vfsId);

    /**
     * 查找默认VFS
     *
     * @param master
     * @return 如果查不到返回null
     */
    VFS findByMaster(Boolean master);
}
