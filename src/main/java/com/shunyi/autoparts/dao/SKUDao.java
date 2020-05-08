package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.exception.SKUNotFoundException;
import com.shunyi.autoparts.model.SKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @description 产品SKU Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface SKUDao extends JpaRepository<SKU, Long>, JpaSpecificationExecutor<SKU> {

    /** 根据产品ID查询全部SKU */
    List<SKU> findAllByProduct_idOrderByIdAsc(Long Product_id);

    /** 根据SKU CODE查询全部SKU */
    List<SKU> findAllBySkuCodeOrderByIdAsc(String skuCode);

    /** 根据SKU名称查询全部SKU */
    List<SKU> findAllBySkuNameOrderByIdAsc(String skuName);

    /**
     * 更新SKU库存数量
     */
    @Modifying
    @Transactional(rollbackOn = SKUNotFoundException.class)
    @Query("update SKU sku set sku.stockQty=:stockQty where sku.id=:id")
    void updateStockQtyBySKUID(@Param(value = "stockQty") int stockQty, @Param(value = "id") Long id);
}
