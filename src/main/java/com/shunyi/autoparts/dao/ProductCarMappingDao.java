package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.ProductCarMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCarMappingDao extends JpaRepository<ProductCarMapping, ProductCarMapping.Id> {

    List<ProductCarMapping> findAllByProductIdOrderByCarIdAsc(Long productId);

    List<ProductCarMapping> findAllByCarIdOrderByProductIdAsc(Long carId);
}
