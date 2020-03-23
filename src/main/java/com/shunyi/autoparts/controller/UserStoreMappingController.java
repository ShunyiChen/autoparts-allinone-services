package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.UserStoreMappingDao;
import com.shunyi.autoparts.model.UserStoreMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 用户和店铺关系控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class UserStoreMappingController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(UserStoreMappingController.class);

    /** 用户店铺映射关系Dao */
    @Autowired
    private UserStoreMappingDao userStoreMappingDao;

    @PostMapping("/userstoremappings")
    public ResponseEntity<?> create(@RequestBody UserStoreMapping.Id[] ids) {
        for(UserStoreMapping.Id id : ids) {
            UserStoreMapping userShopMapping = new UserStoreMapping();
            userShopMapping.setId(id);
            userStoreMappingDao.save(userShopMapping);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/userstoremappings/user/{uid}")
    public List<UserStoreMapping> retrieveAllByUserId(@PathVariable Long uid) {
        return userStoreMappingDao.findAllByUserIdOrderByShopIdAsc(uid);
    }

    @GetMapping("/userstoremappings/shop/{sid}")
    public List<UserStoreMapping> retrieveAllByShopId(@PathVariable Long sid) {
        return userStoreMappingDao.findAllByShopIdOrderByUserIdAsc(sid);
    }

    @DeleteMapping("/userstoremappings")
    public void delete(@RequestBody UserStoreMapping.Id[] ids) {
        for(UserStoreMapping.Id id : ids) {
            userStoreMappingDao.deleteById(id);
        }
    }
}
