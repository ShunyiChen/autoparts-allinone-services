package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.StoreDao;
import com.shunyi.autoparts.dao.UserStoreMappingDao;
import com.shunyi.autoparts.exception.StoreNotFoundException;
import com.shunyi.autoparts.model.Store;
import com.shunyi.autoparts.model.UserStoreMapping;
import com.shunyi.autoparts.model.Warehouse;
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
    private StoreDao storeDao;
    /** 用户与店铺关系Dao */
    @Autowired
    private UserStoreMappingDao userStoreMappingDao;

    @PostMapping("/stores")
    public ResponseEntity<?> create(@RequestBody Store store) {
        Store savedStore = storeDao.save(store);
        return new ResponseEntity<>(savedStore.getId(), HttpStatus.OK);
    }

    @PutMapping("/stores/{id}")
    public ResponseEntity<?> update(@RequestBody Store store, @PathVariable Long id) {
        Optional<Store> storeOptional = storeDao.findById(id);
        if (!storeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        store.setId(id);
        storeDao.save(store);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/stores/{id}")
    public void delete(@PathVariable Long id) {
        storeDao.deleteById(id);
    }

    @GetMapping("/stores")
    public List<Store> retrieveAll() {
        return storeDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/stores/user/{uid}")
    public List<Store> retrieveAllByUserId(@PathVariable Long uid) {
        List<UserStoreMapping> list = userStoreMappingDao.findAllByUserIdOrderByStoreIdAsc(uid);
        List<Store> lstStores = new ArrayList<>();
        list.forEach(e -> {
            Optional<Store> opt = storeDao.findById(e.getId().getStoreId());
            if(opt.isPresent()) {
                lstStores.add(opt.get());
            }
        });
        return lstStores;
    }

    @GetMapping("/stores/{id}")
    public Store retrieve(@PathVariable Long id) {
        Optional<Store> store = storeDao.findById(id);
        if (!store.isPresent()) {
            throw new StoreNotFoundException("Store not found with id -" + id);
        }
        return store.get();
    }

    @GetMapping("/stores/name/{name}")
    public Store retrieve(@PathVariable String name) {
        List<Store> stores = storeDao.findAllByNameOrderByIdAsc(name);
        if (stores.size() == 0) {
            return new Store();
        }
        return stores.get(0);
    }
}