package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.StoreDao;
import com.shunyi.autoparts.dao.UserStoreMappingDao;
import com.shunyi.autoparts.exception.StoreNotFoundException;
import com.shunyi.autoparts.model.Store;
import com.shunyi.autoparts.model.UserStoreMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @description 店铺控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class StoreController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
    @Autowired
    private StoreDao shopDao;
    /** 用户与店铺关系Dao */
    @Autowired
    private UserStoreMappingDao userShopMappingDao;

    @PostMapping("/shops")
    public ResponseEntity<?> create(@RequestBody Store department) {
        Store savedShop = shopDao.save(department);
        return new ResponseEntity<>(savedShop.getId(), HttpStatus.OK);
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<?> update(@RequestBody Store department, @PathVariable Long id) {
        Optional<Store> departmentOptional = shopDao.findById(id);
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
    public List<Store> retrieveAll() {
        return shopDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/shops/user/{uid}")
    public List<Store> retrieveAllByUserId(@PathVariable Long uid) {
        List<UserStoreMapping> list = userShopMappingDao.findAllByUserIdOrderByShopIdAsc(uid);
        List<Store> lstShops = new ArrayList<>();
        list.forEach(e -> {
            Optional<Store> opt = shopDao.findById(e.getId().getShopId());
            if(opt.isPresent()) {
                lstShops.add(opt.get());
            }
        });
        return lstShops;
    }

    @GetMapping("/shops/{id}")
    public Store retrieve(@PathVariable Long id) {
        Optional<Store> department = shopDao.findById(id);
        if (!department.isPresent())
            throw new StoreNotFoundException("Store not found with id -" + id);
        return department.get();
    }
}