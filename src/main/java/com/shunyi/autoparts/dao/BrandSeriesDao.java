package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.BrandSeries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** 品牌系列Dao */
public interface BrandSeriesDao extends JpaRepository<BrandSeries, Long>  {

    List<BrandSeries> findAllByCategory_idOrderByIdAsc(Long Category_id);
}
