package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PackageDao;
import com.shunyi.autoparts.exception.PackageNotFoundException;
import com.shunyi.autoparts.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 打包品控制器 */
@RestController
@CrossOrigin
public class PackageController {

    @Autowired
    private PackageDao packageDao;

    @PostMapping("/packages")
    public ResponseEntity<?> create(@RequestBody Package apackage) {
        Package savedPackage = packageDao.save(apackage);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPackage.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/packages/{id}")
    public ResponseEntity<?> update(@RequestBody Package apackage, @PathVariable Long id) {
        Optional<Package> packageOptional = packageDao.findById(id);
        if (!packageOptional.isPresent())
            return ResponseEntity.notFound().build();
        apackage.setId(id);
        packageDao.save(apackage);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/packages/{id}")
    public void delete(@PathVariable Long id) {
        packageDao.deleteById(id);
    }

    @GetMapping("/packages")
    public List<Package> retrieveAll() {
        return packageDao.findAll();
    }

    @GetMapping("/packages/{id}")
    public Package retrieve(@PathVariable Long id) {
        Optional<Package> aPackage = packageDao.findById(id);
        if (!aPackage.isPresent())
            throw new PackageNotFoundException("Package not found with id -" + id);
        return aPackage.get();
    }
}