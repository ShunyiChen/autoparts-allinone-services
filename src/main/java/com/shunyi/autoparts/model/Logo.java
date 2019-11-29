package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 品牌Logo表 */
@Entity
@Table(name = "logos")
public class Logo {
    /** 自增长ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 图片路径 */
    private String path;
    /** 图片所在的文件系统 */
    @ManyToOne
    @JoinColumn(name = "logo_vfs_id",
            foreignKey = @ForeignKey(name = "LOGO_VFS_ID_FK")
    )
    private VFS vfs;

    public Logo() {}

    public Logo(String path, VFS vfs) {
        this.path = path;
        this.vfs = vfs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public VFS getVfs() {
        return vfs;
    }

    public void setVfs(VFS vfs) {
        this.vfs = vfs;
    }

    @Override
    public String toString() {
        return "Logo{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", vfs=" + vfs +
                '}';
    }
}
