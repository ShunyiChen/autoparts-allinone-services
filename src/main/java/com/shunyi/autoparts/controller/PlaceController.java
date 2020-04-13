package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PlaceDao;
import com.shunyi.autoparts.exception.PlaceNotFoundException;
import com.shunyi.autoparts.model.Place;
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
 * @description 配件产地控制器
 * @author Shunyi Chen
 * @date 2020/4/13
 */
@RestController
@CrossOrigin
public class PlaceController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
    @Autowired
    private PlaceDao placeDao;

    @PostMapping("/places")
    public ResponseEntity<?> create(@RequestBody Place place) {
        List<Place> places = placeDao.findAll();
        Optional<Place> findAny = places.parallelStream().filter(c -> c.getName().equals(place.getName())).findAny();
        if(!findAny.isPresent()) {
            Place savedPlace = placeDao.save(place);
            return new ResponseEntity<>(savedPlace.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/places/{id}")
    public ResponseEntity<?> update(@RequestBody Place place, @PathVariable Long id) {
        Optional<Place> carOptional = placeDao.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        place.setId(id);
        placeDao.save(place);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/places/{id}")
    public void delete(@PathVariable Long id) {
        placeDao.deleteById(id);
    }

    @GetMapping("/places")
    public List<Place> retrieveAll() {
        return placeDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/places/{id}")
    public Place retrieve(@PathVariable Long id) {
        Optional<Place> place = placeDao.findById(id);
        if (!place.isPresent()) {
            throw new PlaceNotFoundException("Place not found with id -" + id);
        }
        return place.get();
    }
}