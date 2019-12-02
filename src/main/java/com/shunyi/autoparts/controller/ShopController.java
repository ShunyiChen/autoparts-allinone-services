package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.ShopDao;
import com.shunyi.autoparts.exception.ShopNotFoundException;
import com.shunyi.autoparts.model.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 店铺控制器 */
@RestController
@CrossOrigin
public class ShopController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    @Autowired
    private ShopDao shopDao;

    @PostMapping("/shops")
    public ResponseEntity<?> create(@RequestBody Shop department) {
        Shop savedShop = shopDao.save(department);
        return new ResponseEntity<>(savedShop.getId(), HttpStatus.OK);
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<?> update(@RequestBody Shop department, @PathVariable Long id) {
        Optional<Shop> departmentOptional = shopDao.findById(id);
        if (!departmentOptional.isPresent())
            return ResponseEntity.notFound().build();
        department.setId(id);
        shopDao.save(department);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/shops/{id}")
    public void delete(@PathVariable Long id) {
        shopDao.deleteById(id);
    }

    @GetMapping("/shops")
    public List<Shop> retrieveAll() {
        return shopDao.findAll();
    }

    @GetMapping("/shops/company/{companyId}")
    public List<Shop> retrieveAll(@PathVariable Long companyId) {
        return shopDao.findAllByCompany_idOrderById(companyId);
    }

    @GetMapping("/shops/{id}")
    public Shop retrieve(@PathVariable Long id) {
        Optional<Shop> department = shopDao.findById(id);
        if (!department.isPresent())
            throw new ShopNotFoundException("Shop not found with id -" + id);
        return department.get();
    }
}