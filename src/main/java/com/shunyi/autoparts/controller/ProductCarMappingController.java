package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.ProductCarMappingDao;
import com.shunyi.autoparts.model.ProductCarMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 配件和车型映射关系Controller
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class ProductCarMappingController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ProductCarMappingController.class);

    /** 用户和角色映射关系Dao */
    @Autowired
    private ProductCarMappingDao productCarMappingDao;

    @PostMapping("/productcarmappings")
    public ResponseEntity<?> create(@RequestBody ProductCarMapping.Id[] ids) {
        for(ProductCarMapping.Id id : ids) {
            ProductCarMapping rolePermissionMapping = new ProductCarMapping();
            rolePermissionMapping.setId(id);
            productCarMappingDao.save(rolePermissionMapping);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/productcarmappings/product/{pid}")
    public List<ProductCarMapping> retrieveAllByRoleId(@PathVariable Long rid) {
        return productCarMappingDao.findAllByProductIdOrderByCarIdAsc(rid);
    }

    @GetMapping("/productcarmappings/car/{pid}")
    public List<ProductCarMapping> retrieveAllByPermissionId(@PathVariable Long cid) {
        return productCarMappingDao.findAllByCarIdOrderByProductIdAsc(cid);
    }

    @DeleteMapping("/productcarmappings")
    public void delete(@RequestBody ProductCarMapping.Id[] ids) {
        for(ProductCarMapping.Id id : ids) {
            productCarMappingDao.deleteById(id);
        }
    }
}
