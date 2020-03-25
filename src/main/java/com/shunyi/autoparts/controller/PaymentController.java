package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PaymentDao;
import com.shunyi.autoparts.exception.PaymentNotFoundException;
import com.shunyi.autoparts.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 支付方式控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class PaymentController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentDao paymentDao;

    @PostMapping("/payments")
    public ResponseEntity<?> create(@RequestBody Payment payment) {
        Payment savedSettlement = paymentDao.save(payment);
        return new ResponseEntity<>(savedSettlement.getId(), HttpStatus.OK);
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<?> update(@RequestBody Payment payment, @PathVariable Long id) {
        Optional<Payment> paymentOptional = paymentDao.findById(id);
        if (!paymentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        payment.setId(id);
        paymentDao.save(payment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/payments/{id}")
    public void delete(@PathVariable Long id) {
        paymentDao.deleteById(id);
    }

    @GetMapping("/payments")
    public List<Payment> retrieveAll() {
        return paymentDao.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment retrieve(@PathVariable Long id) {
        Optional<Payment> payment = paymentDao.findById(id);
        if (!payment.isPresent()) {
            throw new PaymentNotFoundException("Payment not found with id -" + id);
        }
        return payment.get();
    }
}