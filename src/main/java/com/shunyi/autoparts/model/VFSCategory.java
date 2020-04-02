package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @description VFS类目
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "vfs_categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @Override
    public String toString() {
        return name;
    }
}
