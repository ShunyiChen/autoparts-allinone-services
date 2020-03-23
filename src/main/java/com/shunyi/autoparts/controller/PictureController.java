package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PictureDao;
import com.shunyi.autoparts.exception.PictureNotFoundException;
import com.shunyi.autoparts.model.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 产品图片控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class PictureController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);
    @Autowired
    private PictureDao pictureDao;

    @PostMapping("/pictures")
    public ResponseEntity<?> create(@RequestBody Picture picture) {
        Picture savedPicture = pictureDao.save(picture);
        return new ResponseEntity<>(savedPicture.getId(), HttpStatus.OK);
    }

    @PutMapping("/pictures/{id}")
    public ResponseEntity<?> update(@RequestBody Picture picture, @PathVariable Long id) {
        Optional<Picture> pictureOptional = pictureDao.findById(id);
        if (!pictureOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        picture.setId(id);
        pictureDao.save(picture);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/pictures/{id}")
    public void delete(@PathVariable Long id) {
        pictureDao.deleteById(id);
    }

    @GetMapping("/pictures")
    public List<Picture> retrieveAll() {
        return pictureDao.findAll();
    }

    @GetMapping("/pictures/{id}")
    public Picture retrieve(@PathVariable Long id) {
        Optional<Picture> picture = pictureDao.findById(id);
        if (!picture.isPresent()) {
            throw new PictureNotFoundException("Picture not found with id -" + id);
        }
        return picture.get();
    }
}