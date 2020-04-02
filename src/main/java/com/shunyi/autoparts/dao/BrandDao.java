package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 配件品牌Dao
 * @author Shunyi Chen
 * @date 2020/4/2
 */
public interface BrandDao extends JpaRepository<Brand, Long>  {
}
