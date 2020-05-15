package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.StocktakingDao;
import com.shunyi.autoparts.exception.StocktakingNotFoundException;
import com.shunyi.autoparts.model.Stocktaking;
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
 * @description 盘点方式Controller
 * @author Shunyi
 * @date 2020/5/14
 */
@RestController
@CrossOrigin
public class StocktakingController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(StocktakingController.class);
    @Autowired
    private StocktakingDao stocktakingDao;

    @PostMapping("/stocktaking")
    public ResponseEntity<?> create(@RequestBody Stocktaking stocktaking) {
        List<Stocktaking> stocktakingList = stocktakingDao.findAll();
        Optional<Stocktaking> findAny = stocktakingList.parallelStream().filter(c -> c.getName().equals(stocktaking.getName())).findAny();
        if(!findAny.isPresent()) {
            Stocktaking savedStocktaking = stocktakingDao.save(stocktaking);
            return new ResponseEntity<>(savedStocktaking.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/stocktaking/{id}")
    public ResponseEntity<?> update(@RequestBody Stocktaking stocktaking, @PathVariable Long id) {
        Optional<Stocktaking> carOptional = stocktakingDao.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        stocktaking.setId(id);
        stocktakingDao.save(stocktaking);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/stocktaking/{id}")
    public void delete(@PathVariable Long id) {
        stocktakingDao.deleteById(id);
    }

    @GetMapping("/stocktaking")
    public List<Stocktaking> retrieveAll() {
        return stocktakingDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/stocktaking/{id}")
    public Stocktaking retrieve(@PathVariable Long id) {
        Optional<Stocktaking> stocktaking = stocktakingDao.findById(id);
        if (!stocktaking.isPresent()) {
            throw new StocktakingNotFoundException("Stocktaking not found with id -" + id);
        }
        return stocktaking.get();
    }
}