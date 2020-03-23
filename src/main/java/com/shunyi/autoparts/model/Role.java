package com.shunyi.autoparts.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @description 角色
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Role {
    /** 角色ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /** 角色名称 */
    @Column(name = "name")
    private String name;
    /** 描述 */
    @Column(name = "description")
    private String description;
    /** 用户与角色映射关系 */
    @OneToMany(mappedBy = "role")
    private Set<UserRoleMapping> userRoleMappingSet = new HashSet<>();
    /** 角色与权限映射关系 */
    @OneToMany(mappedBy = "role")
    private Set<RolePermissionMapping> rolePermissionMappingSet = new HashSet<>();
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
