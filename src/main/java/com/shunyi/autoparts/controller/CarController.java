package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.CarDao;
import com.shunyi.autoparts.exception.CarNotFoundException;
import com.shunyi.autoparts.model.Car;
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
 * @description 车型控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class CarController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    @Autowired
    private CarDao carDao;

    @PostMapping("/cars")
    public ResponseEntity<?> create(@RequestBody Car car) {
        Car savedCar = carDao.save(car);
        return new ResponseEntity<>(savedCar.getId(), HttpStatus.OK);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<?> update(@RequestBody Car car, @PathVariable Long id) {
        Optional<Car> carOptional = carDao.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        car.setId(id);
        carDao.save(car);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cars/{id}")
    public void delete(@PathVariable Long id) {
        carDao.deleteById(id);
    }

    @GetMapping("/cars")
    public List<Car> retrieveAll() {
        return carDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/cars/{id}")
    public Car retrieve(@PathVariable Long id) {
        Optional<Car> car = carDao.findById(id);
        if (!car.isPresent()) {
            throw new CarNotFoundException("Car not found with id -" + id);
        }
        return car.get();
    }
}