package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.BrandSeries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description 品牌系列Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface BrandSeriesDao extends JpaRepository<BrandSeries, Long>  {

    List<BrandSeries> findAllByCategory_idOrderByIdAsc(Long Category_id);
}
