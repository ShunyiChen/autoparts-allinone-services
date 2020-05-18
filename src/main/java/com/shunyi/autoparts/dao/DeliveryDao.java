package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description 发货方式Dao
 * @author Shunyi
 * @date 2020/5/12
 */
public interface DeliveryDao extends JpaRepository<Delivery, Long>, JpaSpecificationExecutor<Delivery> {
}