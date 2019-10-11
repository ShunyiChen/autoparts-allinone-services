package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

/** 产品图片Dao */
public interface PictureDao extends JpaRepository<Picture, Long> {
}
