package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.Date;

/** 产品图片表 */
@Entity
@Table(name = "pictures")
public class Picture {
    /** 自增长ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 产品ID */
    private Long projectId;
    /** 图片路径 */
    private String path;
    /** 图片所在的文件系统 */
    @ManyToOne
    @JoinColumn(name = "picture_vfs_id",
            foreignKey = @ForeignKey(name = "PICTURE_VFS_ID_FK")
    )
    private VFS vfs;
    /** 创建时间 */
    private Date dateCreated;

    public Picture() {}

    public Picture(Long projectId, String path, VFS vfs, Date dateCreated) {
        this.projectId = projectId;
        this.path = path;
        this.vfs = vfs;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", path='" + path + '\'' +
                ", vfs=" + vfs +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
