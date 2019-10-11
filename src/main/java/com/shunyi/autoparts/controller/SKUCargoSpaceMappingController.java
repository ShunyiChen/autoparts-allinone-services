package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SKUCargoSpaceDao;
import com.shunyi.autoparts.exception.SKUCargoSpaceNotFoundException;
import com.shunyi.autoparts.model.SKUCargoSpaceMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 产品类目控制器 */
@RestController
@CrossOrigin
public class SKUCargoSpaceMappingController {

    @Autowired
    private SKUCargoSpaceDao skuCargoSpaceDao;

    @PostMapping("/skuCargoSpaceMappings")
    public ResponseEntity<?> create(@RequestBody SKUCargoSpaceMapping skuCargoSpace) {
        SKUCargoSpaceMapping savedSKUCargoSpace = skuCargoSpaceDao.save(skuCargoSpace);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSKUCargoSpace.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/skuCargoSpaceMappings/{id}")
    public ResponseEntity<?> update(@RequestBody SKUCargoSpaceMapping skuCargoSpace, @PathVariable SKUCargoSpaceMapping.Id id) {
        Optional<SKUCargoSpaceMapping> skuCargoSpaceOptional = skuCargoSpaceDao.findById(id);
        if (!skuCargoSpaceOptional.isPresent())
            return ResponseEntity.notFound().build();
        skuCargoSpace.setId(id);
        skuCargoSpaceDao.save(skuCargoSpace);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/skuCargoSpaceMappings/{id}")
    public void delete(@PathVariable SKUCargoSpaceMapping.Id id) {
        skuCargoSpaceDao.deleteById(id);
    }

    @GetMapping("/skuCargoSpaceMappings")
    public List<SKUCargoSpaceMapping> retrieveAll() {
        return skuCargoSpaceDao.findAll();
    }

    @GetMapping("/skuCargoSpaceMappings/{id}")
    public SKUCargoSpaceMapping retrieve(@PathVariable SKUCargoSpaceMapping.Id id) {
        Optional<SKUCargoSpaceMapping> skuCargoSpace = skuCargoSpaceDao.findById(id);
        if (!skuCargoSpace.isPresent())
            throw new SKUCargoSpaceNotFoundException("SKUCargoSpaceMapping not found with id -" + id);
        return skuCargoSpace.get();
    }
}