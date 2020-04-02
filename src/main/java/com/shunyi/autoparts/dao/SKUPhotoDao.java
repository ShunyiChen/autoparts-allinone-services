package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SKUPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 配件SKU图片品牌Dao
 * @author Shunyi Chen
 * @date 2020/4/2
 */
public interface SKUPhotoDao extends JpaRepository<SKUPhoto, Long>  {
}
