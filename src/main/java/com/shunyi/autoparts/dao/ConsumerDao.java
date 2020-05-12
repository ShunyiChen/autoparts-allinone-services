package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description 客户Dao
 * @author Shunyi
 * @date 2020/5/12
 */
public interface ConsumerDao extends JpaRepository<Consumer, Long>, JpaSpecificationExecutor<Consumer> {

    /**
     * 根据分类取客户列表
     *
     * @param consumerCategoryId
     * @return
     */
    List<Consumer> findAllByConsumerCategoryIdOrderByIdAsc(Long consumerCategoryId);
}
