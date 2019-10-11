package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.BrandSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/** 品牌系列Dao */
public interface BrandSeriesDao extends JpaRepository<BrandSeries, Long>  {
}
