package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PictureDao;
import com.shunyi.autoparts.exception.PictureNotFoundException;
import com.shunyi.autoparts.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 产品图片控制器 */
@RestController
@CrossOrigin
public class PictureController {

    @Autowired
    private PictureDao pictureDao;

    @PostMapping("/pictures")
    public ResponseEntity<?> create(@RequestBody Picture picture) {
        Picture savedPicture = pictureDao.save(picture);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPicture.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/pictures/{id}")
    public ResponseEntity<?> update(@RequestBody Picture picture, @PathVariable Long id) {
        Optional<Picture> pictureOptional = pictureDao.findById(id);
        if (!pictureOptional.isPresent())
            return ResponseEntity.notFound().build();
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
        if (!picture.isPresent())
            throw new PictureNotFoundException("Picture not found with id -" + id);
        return picture.get();
    }
}