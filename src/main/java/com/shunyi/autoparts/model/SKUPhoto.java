package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description SKU图片
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "sku_photos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SKUPhoto {
    /** 自增长ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 图片名 */
    private String name;
    /** 图片路径 */
    private String path;
    /** 图片所在VFS文件系统 */
    @ManyToOne
    @JoinColumn(name = "picture_vfs_id",
            foreignKey = @ForeignKey(name = "PICTURE_VFS_ID_FK")
    )
    private VFS vfs;
}
