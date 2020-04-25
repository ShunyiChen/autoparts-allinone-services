package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.AccountDao;
import com.shunyi.autoparts.exception.AccountNotFoundException;
import com.shunyi.autoparts.model.Account;
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
 * @description 进货单账号控制器
 * @author Shunyi Chen
 * @date 2020/4/18
 */
@RestController
@CrossOrigin
public class AccountController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountDao accountDao;

    @PostMapping("/accounts")
    public ResponseEntity<?> create(@RequestBody Account account) {
        List<Account> accounts = accountDao.findAll();
        Optional<Account> findAny = accounts.parallelStream().filter(c -> c.getName().equals(account.getName())).findAny();
        if(!findAny.isPresent()) {
            Account savedAccount = accountDao.save(account);
            return new ResponseEntity<>(savedAccount.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> update(@RequestBody Account account, @PathVariable Long id) {
        Optional<Account> accountOptional = accountDao.findById(id);
        if (!accountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        account.setId(id);
        accountDao.save(account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable Long id) {
        accountDao.deleteById(id);
    }

    @GetMapping("/accounts")
    public List<Account> retrieveAll() {
        return accountDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/accounts/{id}")
    public Account retrieve(@PathVariable Long id) {
        Optional<Account> account = accountDao.findById(id);
        if (!account.isPresent()) {
            throw new AccountNotFoundException("Account not found with id -" + id);
        }
        return account.get();
    }
}