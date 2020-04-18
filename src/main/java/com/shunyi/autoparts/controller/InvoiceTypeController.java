package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.InvoiceTypeDao;
import com.shunyi.autoparts.exception.InvoiceTypeNotFoundException;
import com.shunyi.autoparts.model.InvoiceType;
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
 * @description 发票类型控制器
 * @author Shunyi Chen
 * @date 2020/4/18
 */
@RestController
@CrossOrigin
public class InvoiceTypeController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(InvoiceTypeController.class);
    @Autowired
    private InvoiceTypeDao invoiceTypeDao;

    @PostMapping("/invoiceTypes")
    public ResponseEntity<?> create(@RequestBody InvoiceType invoiceType) {
        List<InvoiceType> invoiceTypes = invoiceTypeDao.findAll();
        Optional<InvoiceType> findAny = invoiceTypes.parallelStream().filter(c -> c.getName().equals(invoiceType.getName())).findAny();
        if(!findAny.isPresent()) {
            InvoiceType savedInvoiceType = invoiceTypeDao.save(invoiceType);
            return new ResponseEntity<>(savedInvoiceType.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/invoiceTypes/{id}")
    public ResponseEntity<?> update(@RequestBody InvoiceType invoiceType, @PathVariable Long id) {
        Optional<InvoiceType> invoiceTypeOptional = invoiceTypeDao.findById(id);
        if (!invoiceTypeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        invoiceType.setId(id);
        invoiceTypeDao.save(invoiceType);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/invoiceTypes/{id}")
    public void delete(@PathVariable Long id) {
        invoiceTypeDao.deleteById(id);
    }

    @GetMapping("/invoiceTypes")
    public List<InvoiceType> retrieveAll() {
        return invoiceTypeDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/invoiceTypes/{id}")
    public InvoiceType retrieve(@PathVariable Long id) {
        Optional<InvoiceType> invoiceType = invoiceTypeDao.findById(id);
        if (!invoiceType.isPresent()) {
            throw new InvoiceTypeNotFoundException("InvoiceType not found with id -" + id);
        }
        return invoiceType.get();
    }
}