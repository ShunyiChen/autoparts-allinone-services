package com.shunyi.autoparts.model;

import javax.persistence.*;

/** VFS类目 */
@Entity
@Table(name = "vfs_categories")
public class VFSCategory {

    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 类目名称 */
    private String name;
    /** 父类目ID */
    private Long parentId;
    /** 是否父节点 */
    private Boolean parent;

    public VFSCategory() {}

    public VFSCategory(String name, Long parentId, Boolean parent) {
        this.name = name;
        this.parentId = parentId;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return name;
    }
}