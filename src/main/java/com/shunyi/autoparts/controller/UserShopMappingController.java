package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.UserShopMappingDao;
import com.shunyi.autoparts.model.UserShopMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** 用户店铺映射关系Controller */
@RestController
@CrossOrigin
public class UserShopMappingController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(UserShopMappingController.class);

    /** 用户店铺映射关系Dao */
    @Autowired
    private UserShopMappingDao userShopMappingDao;

    @PostMapping("/usershopmappings")
    public ResponseEntity<?> create(@RequestBody UserShopMapping.Id[] ids) {
        for(UserShopMapping.Id id : ids) {
            UserShopMapping userShopMapping = new UserShopMapping();
            userShopMapping.setId(id);
            userShopMappingDao.save(userShopMapping);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/usershopmappings/user/{uid}")
    public List<UserShopMapping> retrieveAllByUserId(@PathVariable Long uid) {
        return userShopMappingDao.findAllByUserIdOrderByShopIdAsc(uid);
    }

    @GetMapping("/usershopmappings/shop/{sid}")
    public List<UserShopMapping> retrieveAllByShopId(@PathVariable Long sid) {
        return userShopMappingDao.findAllByShopIdOrderByUserIdAsc(sid);
    }

    @DeleteMapping("/usershopmappings")
    public void delete(@RequestBody UserShopMapping.Id[] ids) {
        for(UserShopMapping.Id id : ids) {
            userShopMappingDao.deleteById(id);
        }
    }
}
