package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 仓库
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "warehouses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Warehouse {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 仓库编号 */
    private String code;
    /** 名称 */
    private String name;
    /** 父节点ID */
    private Long parentId;
    /** 是否是父节点 */
    private Boolean parent;
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
