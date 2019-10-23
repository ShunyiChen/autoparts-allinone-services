package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.WarehouseDao;
import com.shunyi.autoparts.exception.WarehouseNotFoundException;
import com.shunyi.autoparts.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 仓库控制器 */
@RestController
@CrossOrigin
public class WarehouseController {
    @Autowired
    private WarehouseDao warehouseDao;

    @PostMapping("/warehouses")
    public ResponseEntity<Long> create(@RequestBody Warehouse warehouse) {
        Warehouse savedWarehouse = warehouseDao.save(warehouse);
        return new ResponseEntity<>(savedWarehouse.getId(), HttpStatus.OK);
    }

    @PutMapping("/warehouses/{id}")
    public ResponseEntity<?> update(@RequestBody Warehouse warehouse, @PathVariable Long id) {
        Optional<Warehouse> warehouseOptional = warehouseDao.findById(id);
        if (!warehouseOptional.isPresent())
            return ResponseEntity.notFound().build();
        warehouse.setId(id);
        warehouseDao.save(warehouse);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/warehouses/{id}")
    public void delete(@PathVariable Long id) {
        warehouseDao.deleteById(id);
    }

    @GetMapping("/warehouses")
    public List<Warehouse> retrieveAll() {
        return warehouseDao.findAll();
    }

    @GetMapping("/warehouses/{id}")
    public Warehouse retrieve(@PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseDao.findById(id);
        if (!warehouse.isPresent())
            throw new WarehouseNotFoundException("Warehouse not found with id -" + id);
        return warehouse.get();
    }
}
