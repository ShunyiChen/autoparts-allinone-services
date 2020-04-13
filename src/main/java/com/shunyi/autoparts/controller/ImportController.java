package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.ImportDao;
import com.shunyi.autoparts.exception.ImportNotFoundException;
import com.shunyi.autoparts.model.Import;
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
 * @description 配件产地控制器
 * @author Shunyi Chen
 * @date 2020/4/11
 */
@RestController
@CrossOrigin
public class ImportController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ImportController.class);
    @Autowired
    private ImportDao importDao;

    @PostMapping("/imports")
    public ResponseEntity<?> create(@RequestBody Import imports) {
        List<Import> imports1 = importDao.findAll();
        Optional<Import> findAny = imports1.parallelStream().filter(c -> c.getName().equals(imports.getName())).findAny();
        if(!findAny.isPresent()) {
            Import savedImport = importDao.save(imports);
            return new ResponseEntity<>(savedImport.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/imports/{id}")
    public ResponseEntity<?> update(@RequestBody Import imports, @PathVariable Long id) {
        Optional<Import> carOptional = importDao.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        imports.setId(id);
        importDao.save(imports);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/imports/{id}")
    public void delete(@PathVariable Long id) {
        importDao.deleteById(id);
    }

    @GetMapping("/imports")
    public List<Import> retrieveAll() {
        return importDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/imports/{id}")
    public Import retrieve(@PathVariable Long id) {
        Optional<Import> imports = importDao.findById(id);
        if (!imports.isPresent()) {
            throw new ImportNotFoundException("Import not found with id -" + id);
        }
        return imports.get();
    }
}