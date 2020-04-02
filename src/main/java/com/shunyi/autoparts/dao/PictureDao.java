package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.SKUPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 产品图片Dao
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public interface PictureDao extends JpaRepository<SKUPhoto, Long> {
}
