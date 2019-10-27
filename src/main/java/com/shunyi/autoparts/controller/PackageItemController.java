package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PackageItemDao;
import com.shunyi.autoparts.exception.PackageItemNotFoundException;
import com.shunyi.autoparts.model.PackageItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 打包品余项控制器 */
@RestController
@CrossOrigin
public class PackageItemController {

    @Autowired
    private PackageItemDao packageItemDao;

    @PostMapping("/packageItems")
    public ResponseEntity<?> create(@RequestBody PackageItem packageItem) {
        PackageItem savedPackageItem = packageItemDao.save(packageItem);
        return new ResponseEntity<>(savedPackageItem.getId(), HttpStatus.OK);
    }

    @PutMapping("/packageItems/{id}")
    public ResponseEntity<?> update(@RequestBody PackageItem packageItem, @PathVariable Long id) {
        Optional<PackageItem> packageItemOptional = packageItemDao.findById(id);
        if (!packageItemOptional.isPresent())
            return ResponseEntity.notFound().build();
        packageItem.setId(id);
        packageItemDao.save(packageItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/packageItems/{id}")
    public void delete(@PathVariable Long id) {
        packageItemDao.deleteById(id);
    }

    @GetMapping("/packageItems")
    public List<PackageItem> retrieveAll() {
        return packageItemDao.findAll();
    }

    @GetMapping("/packageItems/{id}")
    public PackageItem retrieve(@PathVariable Long id) {
        Optional<PackageItem> packageItem = packageItemDao.findById(id);
        if (!packageItem.isPresent())
            throw new PackageItemNotFoundException("Package item not found with id -" + id);
        return packageItem.get();
    }
}