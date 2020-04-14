package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.CompanyDao;
import com.shunyi.autoparts.exception.CompanyNotFoundException;
import com.shunyi.autoparts.model.Company;
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
 * @description 所属公司控制器
 * @author Shunyi Chen
 * @date 2020/4/11
 */
@RestController
@CrossOrigin
public class CompanyController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private CompanyDao companyDao;

    @PostMapping("/companies")
    public ResponseEntity<?> create(@RequestBody Company company) {
        List<Company> companies = companyDao.findAll();
        Optional<Company> findAny = companies.parallelStream().filter(c -> c.getName().equals(company.getName())).findAny();
        if(!findAny.isPresent()) {
            Company savedCompany = companyDao.save(company);
            return new ResponseEntity<>(savedCompany.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<?> update(@RequestBody Company company, @PathVariable Long id) {
        Optional<Company> carOptional = companyDao.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        company.setId(id);
        companyDao.save(company);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/companies/{id}")
    public void delete(@PathVariable Long id) {
        companyDao.deleteById(id);
    }

    @GetMapping("/companies")
    public List<Company> retrieveAll() {
        return companyDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/companies/{id}")
    public Company retrieve(@PathVariable Long id) {
        Optional<Company> company = companyDao.findById(id);
        if (!company.isPresent()) {
            throw new CompanyNotFoundException("Company not found with id -" + id);
        }
        return company.get();
    }
}