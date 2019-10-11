package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.CargoSpaceDao;
import com.shunyi.autoparts.exception.CargoSpaceNotFoundException;
import com.shunyi.autoparts.model.CargoSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 货位控制器 */
@RestController
@CrossOrigin
public class CargoSpaceController {

    @Autowired
    private CargoSpaceDao cargoSpaceDao;

    @PostMapping("/cargoSpaces")
    public ResponseEntity<?> create(@RequestBody CargoSpace cargoSpace) {
        CargoSpace savedCargoSpace = cargoSpaceDao.save(cargoSpace);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCargoSpace.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/cargoSpaces/{id}")
    public ResponseEntity<?> update(@RequestBody CargoSpace cargoSpace, @PathVariable Long id) {
        Optional<CargoSpace> cargoSpaceOptional = cargoSpaceDao.findById(id);
        if (!cargoSpaceOptional.isPresent())
            return ResponseEntity.notFound().build();
        cargoSpace.setId(id);
        cargoSpaceDao.save(cargoSpace);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cargoSpaces/{id}")
    public void delete(@PathVariable Long id) {
        cargoSpaceDao.deleteById(id);
    }

    @GetMapping("/cargoSpaces")
    public List<CargoSpace> retrieveAll() {
        return cargoSpaceDao.findAll();
    }

    @GetMapping("/cargoSpaces/{id}")
    public CargoSpace retrieve(@PathVariable Long id) {
        Optional<CargoSpace> cargoSpace = cargoSpaceDao.findById(id);
        if (!cargoSpace.isPresent())
            throw new CargoSpaceNotFoundException("CargoSpace not found with id -" + id);
        return cargoSpace.get();
    }
}
