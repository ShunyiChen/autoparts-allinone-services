package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SettlementDao;
import com.shunyi.autoparts.exception.SettlementNotFoundException;
import com.shunyi.autoparts.model.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 结算方式控制器 */
@RestController
@CrossOrigin
public class SettlementController {

    @Autowired
    private SettlementDao settlementDao;

    @PostMapping("/settlements")
    public ResponseEntity<?> create(@RequestBody Settlement settlement) {
        Settlement savedSettlement = settlementDao.save(settlement);
        return new ResponseEntity<>(savedSettlement.getId(), HttpStatus.OK);
    }

    @PutMapping("/settlements/{id}")
    public ResponseEntity<?> update(@RequestBody Settlement settlement, @PathVariable Long id) {
        Optional<Settlement> settlementOptional = settlementDao.findById(id);
        if (!settlementOptional.isPresent())
            return ResponseEntity.notFound().build();
        settlement.setId(id);
        settlementDao.save(settlement);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/settlements/{id}")
    public void delete(@PathVariable Long id) {
        settlementDao.deleteById(id);
    }

    @GetMapping("/settlements")
    public List<Settlement> retrieveAll() {
        return settlementDao.findAll();
    }

    @GetMapping("/settlements/{id}")
    public Settlement retrieve(@PathVariable Long id) {
        Optional<Settlement> settlement = settlementDao.findById(id);
        if (!settlement.isPresent())
            throw new SettlementNotFoundException("Settlement not found with id -" + id);
        return settlement.get();
    }
}