package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.BrandSeriesDao;
import com.shunyi.autoparts.exception.BrandSeriesNotFoundException;
import com.shunyi.autoparts.model.BrandSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 品牌系列控制器 */
@RestController
@CrossOrigin
public class BrandSeriesController {

    @Autowired
    private BrandSeriesDao brandSeriesDao;

    @PostMapping("/brandSeries")
    public ResponseEntity<?> create(@RequestBody BrandSeries brandSeries) {
        BrandSeries savedBrandSeries = brandSeriesDao.save(brandSeries);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBrandSeries.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/brandSeries/{id}")
    public ResponseEntity<?> update(@RequestBody BrandSeries brandSeries, @PathVariable Long id) {
        Optional<BrandSeries> brandSeriesOptional = brandSeriesDao.findById(id);
        if (!brandSeriesOptional.isPresent())
            return ResponseEntity.notFound().build();
        brandSeries.setId(id);
        brandSeriesDao.save(brandSeries);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/brandSeries/{id}")
    public void delete(@PathVariable Long id) {
        brandSeriesDao.deleteById(id);
    }

    @GetMapping("/brandSeries")
    public List<BrandSeries> retrieveAll() {
        return brandSeriesDao.findAll();
    }

    @GetMapping("/brandSeries/{id}")
    public BrandSeries retrieve(@PathVariable Long id) {
        Optional<BrandSeries> brandSeries = brandSeriesDao.findById(id);
        if (!brandSeries.isPresent())
            throw new BrandSeriesNotFoundException("BrandSeries not found with id -" + id);
        return brandSeries.get();
    }
}
