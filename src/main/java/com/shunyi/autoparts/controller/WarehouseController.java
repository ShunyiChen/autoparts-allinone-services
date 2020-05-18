package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.CategoryDao;
import com.shunyi.autoparts.dao.WarehouseDao;
import com.shunyi.autoparts.exception.WarehouseNotFoundException;
import com.shunyi.autoparts.model.Category;
import com.shunyi.autoparts.model.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 仓库控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class WarehouseController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);
    @Autowired
    private WarehouseDao warehouseDao;
    @Autowired
    private CategoryDao categoryDao;

    @PostMapping("/warehouses")
    public ResponseEntity<Long> create(@RequestBody Warehouse warehouse) {
        Warehouse savedWarehouse = warehouseDao.save(warehouse);
        //创建一个产品根分类
        Category rootCategory = new Category(0L, "全部分类", 0L, true, savedWarehouse);
        categoryDao.save(rootCategory);

        return new ResponseEntity<>(savedWarehouse.getId(), HttpStatus.OK);
    }

    @PutMapping("/warehouses/{id}")
    public ResponseEntity<?> update(@RequestBody Warehouse warehouse, @PathVariable Long id) {
        Optional<Warehouse> warehouseOptional = warehouseDao.findById(id);
        if (!warehouseOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        warehouse.setId(id);
        warehouseDao.save(warehouse);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/warehouses/{id}")
    public void delete(@PathVariable Long id) {
        warehouseDao.deleteById(id);
    }

    @GetMapping("/warehouses")
    public List<Warehouse> retrieveAll() {
        return warehouseDao.findAll();
    }

    @GetMapping("/warehouses/{id}")
    public Warehouse retrieve(@PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseDao.findById(id);
        if (!warehouse.isPresent()) {
            throw new WarehouseNotFoundException("Warehouse not found with id -" + id);
        }
        return warehouse.get();
    }

    @GetMapping("/warehouses/name/{name}")
    public Warehouse retrieve(@PathVariable String name) {
        List<Warehouse> warehouses = warehouseDao.findAllByNameOrderByIdAsc(name);
        if (warehouses.size() == 0) {
            return new Warehouse();
        }
        return warehouses.get(0);
    }
}
