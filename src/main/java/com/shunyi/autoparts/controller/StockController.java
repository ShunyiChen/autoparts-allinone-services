package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.StockDao;
import com.shunyi.autoparts.exception.StockNotFoundException;
import com.shunyi.autoparts.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 产品库存控制器 */
@RestController
@CrossOrigin
public class StockController {

    @Autowired
    private StockDao stockDao;

    @PostMapping("/stocks")
    public ResponseEntity<?> create(@RequestBody Stock stock) {
        Stock savedStock = stockDao.save(stock);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStock.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<?> update(@RequestBody Stock stock, @PathVariable Long id) {
        Optional<Stock> stockOptional = stockDao.findById(id);
        if (!stockOptional.isPresent())
            return ResponseEntity.notFound().build();
        stock.setId(id);
        stockDao.save(stock);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/stocks/{id}")
    public void delete(@PathVariable Long id) {
        stockDao.deleteById(id);
    }

    @GetMapping("/stocks")
    public List<Stock> retrieveAll() {
        return stockDao.findAll();
    }

    @GetMapping("/stocks/{id}")
    public Stock retrieve(@PathVariable Long id) {
        Optional<Stock> stock = stockDao.findById(id);
        if (!stock.isPresent())
            throw new StockNotFoundException("Stock not found with id -" + id);
        return stock.get();
    }
}