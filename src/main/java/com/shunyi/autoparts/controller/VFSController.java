package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.VFSDao;
import com.shunyi.autoparts.exception.VFSNotFoundException;
import com.shunyi.autoparts.model.VFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** VFS控制器 */
@RestController
@CrossOrigin
public class VFSController {

    @Autowired
    private VFSDao vfsDao;

    @PostMapping("/vfs")
    public ResponseEntity<?> create(@RequestBody VFS vfs) {
        VFS savedVFS = vfsDao.save(vfs);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedVFS.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/vfs/{id}")
    public ResponseEntity<?> update(@RequestBody VFS vfs, @PathVariable Long id) {
        Optional<VFS> vfsOptional = vfsDao.findById(id);
        if (!vfsOptional.isPresent())
            return ResponseEntity.notFound().build();
        vfs.setId(id);
        vfsDao.save(vfs);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vfs/{id}")
    public void delete(@PathVariable Long id) {
        vfsDao.deleteById(id);
    }

    @GetMapping("/vfs")
    public List<VFS> retrieveAll() {
        return vfsDao.findAll();
    }

    @GetMapping("/vfs/{id}")
    public VFS retrieve(@PathVariable Long id) {
        Optional<VFS> vfs = vfsDao.findById(id);
        if (!vfs.isPresent())
            throw new VFSNotFoundException("VFS not found with id -" + id);
        return vfs.get();
    }
}