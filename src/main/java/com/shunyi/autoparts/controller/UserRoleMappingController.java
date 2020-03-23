package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.UserRoleMappingDao;
import com.shunyi.autoparts.model.UserRoleMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 用户和角色关系控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class UserRoleMappingController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(UserRoleMappingController.class);

    /** 用户和角色映射关系Dao */
    @Autowired
    private UserRoleMappingDao userRoleMappingDao;

    @PostMapping("/userrolemappings")
    public ResponseEntity<?> create(@RequestBody UserRoleMapping.Id[] ids) {
        for(UserRoleMapping.Id id : ids) {
            UserRoleMapping userRoleMapping = new UserRoleMapping();
            userRoleMapping.setId(id);
            userRoleMappingDao.save(userRoleMapping);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/userrolemappings/user/{uid}")
    public List<UserRoleMapping> retrieveAllByUserId(@PathVariable Long uid) {
        return userRoleMappingDao.findAllByUserIdOrderByRoleIdAsc(uid);
    }

    @GetMapping("/userrolemappings/role/{rid}")
    public List<UserRoleMapping> retrieveAllByRoleId(@PathVariable Long rid) {
        return userRoleMappingDao.findAllByRoleIdOrderByUserIdAsc(rid);
    }

    @DeleteMapping("/userrolemappings")
    public void delete(@RequestBody UserRoleMapping.Id[] ids) {
        for(UserRoleMapping.Id id : ids) {
            userRoleMappingDao.deleteById(id);
        }
    }
}
