package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 配件产地Dao
 * @author Shunyi Chen
 * @date 2020/4/2
 */
public interface PlaceDao extends JpaRepository<Place, Long>  {
}
