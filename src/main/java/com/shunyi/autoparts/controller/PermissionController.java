package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PermissionDao;
import com.shunyi.autoparts.exception.PermissionNotFoundException;
import com.shunyi.autoparts.model.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 权限控制器 */
@RestController
@CrossOrigin
public class PermissionController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private PermissionDao permissionDao;

    @PostMapping("/permissions")
    public ResponseEntity<?> create(@RequestBody Permission department) {
        Permission savedPermission = permissionDao.save(department);
        return new ResponseEntity<>(savedPermission.getId(), HttpStatus.OK);
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity<?> update(@RequestBody Permission department, @PathVariable Long id) {
        Optional<Permission> departmentOptional = permissionDao.findById(id);
        if (!departmentOptional.isPresent())
            return ResponseEntity.notFound().build();
        department.setId(id);
        permissionDao.save(department);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/permissions/{id}")
    public void delete(@PathVariable Long id) {
        permissionDao.deleteById(id);
    }

    @GetMapping("/permissions")
    public List<Permission> retrieveAll() {
        return permissionDao.findAll();
    }

    @GetMapping("/permissions/{id}")
    public Permission retrieve(@PathVariable Long id) {
        Optional<Permission> department = permissionDao.findById(id);
        if (!department.isPresent())
            throw new PermissionNotFoundException("Permission not found with id -" + id);
        return department.get();
    }
}