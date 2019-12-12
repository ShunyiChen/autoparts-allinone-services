package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.RolePermissionMappingDao;
import com.shunyi.autoparts.model.RolePermissionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** 角色和权限映射关系Controller */
@RestController
@CrossOrigin
public class RolePermissionMappingController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RolePermissionMappingController.class);

    /** 用户和角色映射关系Dao */
    @Autowired
    private RolePermissionMappingDao rolePermissionMappingDao;

    @PostMapping("/rolepermissionmappings")
    public ResponseEntity<?> create(@RequestBody RolePermissionMapping.Id[] ids) {
        for(RolePermissionMapping.Id id : ids) {
            RolePermissionMapping rolePermissionMapping = new RolePermissionMapping();
            rolePermissionMapping.setId(id);
            rolePermissionMappingDao.save(rolePermissionMapping);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rolepermissionmappings/role/{rid}")
    public List<RolePermissionMapping> retrieveAllByRoleId(@PathVariable Long rid) {
        return rolePermissionMappingDao.findAllByRoleIdOrderByPermissionIdAsc(rid);
    }

    @GetMapping("/rolepermissionmappings/permission/{pid}")
    public List<RolePermissionMapping> retrieveAllByPermissionId(@PathVariable Long pid) {
        return rolePermissionMappingDao.findAllByPermissionIdOrderByRoleIdAsc(pid);
    }

    @DeleteMapping("/rolepermissionmappings")
    public void delete(@RequestBody RolePermissionMapping.Id[] ids) {
        for(RolePermissionMapping.Id id : ids) {
            rolePermissionMappingDao.deleteById(id);
        }
    }
}
