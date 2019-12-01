package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.RoleDao;
import com.shunyi.autoparts.exception.RoleNotFoundException;
import com.shunyi.autoparts.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 角色控制器 */
@RestController
@CrossOrigin
public class RoleController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleDao roleDao;

    @PostMapping("/roles")
    public ResponseEntity<?> create(@RequestBody Role department) {
        Role savedRole = roleDao.save(department);
        return new ResponseEntity<>(savedRole.getId(), HttpStatus.OK);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> update(@RequestBody Role department, @PathVariable Long id) {
        Optional<Role> departmentOptional = roleDao.findById(id);
        if (!departmentOptional.isPresent())
            return ResponseEntity.notFound().build();
        department.setId(id);
        roleDao.save(department);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/roles/{id}")
    public void delete(@PathVariable Long id) {
        roleDao.deleteById(id);
    }

    @GetMapping("/roles")
    public List<Role> retrieveAll() {
        return roleDao.findAll();
    }

    @GetMapping("/roles/{id}")
    public Role retrieve(@PathVariable Long id) {
        Optional<Role> department = roleDao.findById(id);
        if (!department.isPresent())
            throw new RoleNotFoundException("Role not found with id -" + id);
        return department.get();
    }
}