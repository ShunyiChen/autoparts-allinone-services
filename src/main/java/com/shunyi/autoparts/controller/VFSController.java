package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.VFSDao;
import com.shunyi.autoparts.exception.VFSNotFoundException;
import com.shunyi.autoparts.model.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** VFS控制器 */
@RestController
@CrossOrigin
public class VFSController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(VFSController.class);
    @Autowired
    private VFSDao vfsDao;

    @PostMapping("/vfs")
    public ResponseEntity<?> create(@RequestBody VFS vfs) {
        VFS savedVFS = vfsDao.save(vfs);
        return new ResponseEntity<>(savedVFS.getId(), HttpStatus.OK);
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

    @PutMapping("/vfs/acquiescent/{vid}")
    public ResponseEntity<?> updateAcquiescent(@PathVariable Long vid) {
        //restore every acquiescent to false
        vfsDao.restoreAcquiescent();
        //update acquiescent to true on specified vfs
        vfsDao.updateAcquiescentByVFSId(vid);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vfs/{id}")
    public void delete(@PathVariable Long id) {
        vfsDao.deleteById(id);
    }

    @GetMapping("/vfs")
    public List<VFS> retrieveAll() {
        return vfsDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/vfs/vfscategory/{pid}")
    public List<VFS> retrieveAll(@PathVariable Long pid) {
        return vfsDao.findAllByCategoryIdOrderById(pid);
    }

    @GetMapping("/vfs/{id}")
    public VFS retrieve(@PathVariable Long id) {
        Optional<VFS> vfs = vfsDao.findById(id);
        if (!vfs.isPresent())
            throw new VFSNotFoundException("VFS not found with id -" + id);
        return vfs.get();
    }

    @GetMapping("/vfs/default")
    public VFS retrieveDefault() {
        return vfsDao.findByAcquiescent(true);
    }
}