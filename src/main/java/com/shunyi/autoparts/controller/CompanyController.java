package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.CompanyDao;
import com.shunyi.autoparts.exception.CompanyNotFoundException;
import com.shunyi.autoparts.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 公司控制器 */
@RestController
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyDao companyDao;

    @PostMapping("/companies")
    public ResponseEntity<?> create(@RequestBody Company company) {
        Company savedCompany = companyDao.save(company);
        return new ResponseEntity<>(savedCompany.getId(), HttpStatus.OK);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<?> update(@RequestBody Company company, @PathVariable Long id) {
        Optional<Company> companyOptional = companyDao.findById(id);
        if (!companyOptional.isPresent())
            return ResponseEntity.notFound().build();
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
        return companyDao.findAll();
    }

    @GetMapping("/companies/{id}")
    public Company retrieve(@PathVariable Long id) {
        Optional<Company> company = companyDao.findById(id);
        if (!company.isPresent())
            throw new CompanyNotFoundException("Company not found with id -" + id);
        return company.get();
    }
}