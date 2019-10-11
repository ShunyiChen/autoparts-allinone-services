package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.DepartmentDao;
import com.shunyi.autoparts.exception.DepartmentNotFoundException;
import com.shunyi.autoparts.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 部门控制器 */
@RestController
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentDao departmentDao;

    @PostMapping("/departments")
    public ResponseEntity<?> create(@RequestBody Department department) {
        Department savedDepartment = departmentDao.save(department);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDepartment.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<?> update(@RequestBody Department department, @PathVariable Long id) {
        Optional<Department> departmentOptional = departmentDao.findById(id);
        if (!departmentOptional.isPresent())
            return ResponseEntity.notFound().build();
        department.setId(id);
        departmentDao.save(department);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/departments/{id}")
    public void delete(@PathVariable Long id) {
        departmentDao.deleteById(id);
    }

    @GetMapping("/departments")
    public List<Department> retrieveAll() {
        return departmentDao.findAll();
    }

    @GetMapping("/departments/{id}")
    public Department retrieve(@PathVariable Long id) {
        Optional<Department> department = departmentDao.findById(id);
        if (!department.isPresent())
            throw new DepartmentNotFoundException("Department not found with id -" + id);
        return department.get();
    }
}