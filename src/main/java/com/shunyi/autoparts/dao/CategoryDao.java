package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {
}
