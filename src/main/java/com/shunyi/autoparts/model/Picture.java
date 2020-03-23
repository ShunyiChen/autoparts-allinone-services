package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 产品图片表
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "pictures")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Picture {
    /** 自增长ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 产品ID */
    private Long projectId;
    /** 图片路径 */
    private String path;
    /** VFS文件系统 */
    @ManyToOne
    @JoinColumn(name = "picture_vfs_id",
            foreignKey = @ForeignKey(name = "PICTURE_VFS_ID_FK")
    )
    private VFS vfs;
    /** 创建时间 */
    private Date dateCreated;
    /** 创建者 */
    private String creator;
    /** 更新时间 */
    private Date dateUpdated;
    /** 更新者 */
    private String updater;
    /** 更新次数 */
    private Integer updatedCount;
    /** 删除时间 */
    private Date dateDeleted;
    /** 删除标记 */
    private Boolean deleteFlag;
    /** 删除者 */
    private String deleter;
}
