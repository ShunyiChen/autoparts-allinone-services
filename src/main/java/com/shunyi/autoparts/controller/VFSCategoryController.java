package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.VFSCategoryDao;
import com.shunyi.autoparts.exception.VFSCategoryNotFoundException;
import com.shunyi.autoparts.model.VFSCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** VFS类目控制器 */
@RestController
@CrossOrigin
public class VFSCategoryController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(VFSCategoryController.class);
    @Autowired
    private VFSCategoryDao vfsCategoryDao;

    @PostMapping("/vfs/categories")
    public ResponseEntity<Long> create(@RequestBody VFSCategory vfsCategory) {
        VFSCategory savedCategory = vfsCategoryDao.save(vfsCategory);
        return new ResponseEntity<>(savedCategory.getId(), HttpStatus.OK);
    }

    @PutMapping("/vfs/categories/{id}")
    public ResponseEntity<?> update(@RequestBody VFSCategory vfsCategory, @PathVariable Long id) {
        Optional<VFSCategory> categoryOptional = vfsCategoryDao.findById(id);
        if (!categoryOptional.isPresent())
            return ResponseEntity.notFound().build();
        vfsCategory.setId(id);
        vfsCategoryDao.save(vfsCategory);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vfs/categories/{id}")
    public void delete(@PathVariable Long id) {
        vfsCategoryDao.deleteById(id);
    }

    @GetMapping("/vfs/categories")
    public List<VFSCategory> retrieveAll() {
        return vfsCategoryDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/vfs/categories/{id}")
    public VFSCategory retrieve(@PathVariable Long id) {
        Optional<VFSCategory> vfsCategory = vfsCategoryDao.findById(id);
        if (!vfsCategory.isPresent())
            throw new VFSCategoryNotFoundException("Supplier category not found with id -" + id);
        return vfsCategory.get();
    }

}
