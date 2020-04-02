package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SKUPhotoDao;
import com.shunyi.autoparts.exception.BrandNotFoundException;
import com.shunyi.autoparts.model.SKUPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description SKU图片Controller
 * @author Shunyi Chen
 * @date 2020/4/2
 */
@RestController
@CrossOrigin
public class SKUPhotoController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SKUPhotoController.class);
    @Autowired
    private SKUPhotoDao skuPhotoDao;

    @PostMapping("/sku/photo")
    public ResponseEntity<?> create(@RequestBody SKUPhoto skuPhoto) {
        SKUPhoto savedBrandSeries = skuPhotoDao.save(skuPhoto);
        return new ResponseEntity<>(savedBrandSeries.getId(), HttpStatus.OK);
    }

    @PutMapping("/sku/photo/{id}")
    public ResponseEntity<?> update(@RequestBody SKUPhoto skuPhoto, @PathVariable Long id) {
        Optional<SKUPhoto> brandSeriesOptional = skuPhotoDao.findById(id);
        if (!brandSeriesOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        skuPhoto.setId(id);
        skuPhotoDao.save(skuPhoto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/sku/photo/{id}")
    public void delete(@PathVariable Long id) {
        skuPhotoDao.deleteById(id);
    }

    @GetMapping("/sku/photo")
    public List<SKUPhoto> retrieveAll() {
        return skuPhotoDao.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @GetMapping("/sku/photo/{id}")
    public SKUPhoto retrieve(@PathVariable Long id) {
        Optional<SKUPhoto> skuPhoto = skuPhotoDao.findById(id);
        if (!skuPhoto.isPresent()) {
            throw new BrandNotFoundException("SKUPhoto not found with id -" + id);
        }
        return skuPhoto.get();
    }
}
