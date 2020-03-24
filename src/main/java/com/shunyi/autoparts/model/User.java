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
 * @description 系统用户
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User {
	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** 用户名 */
	private String username;
	/** 姓名 */
	private String chineseName;
	/** 英文名 */
	private String englishName;
	/** 性别 */
	private String sex;
	/** 电话 */
	private String phone;
	/** 电子邮件 */
	private String email;
	/** 加密的密码 */
	private String password;
	/** 是否激活 */
	private Boolean enabled;
	/** 用户和店铺映射关系 */
	@OneToMany(mappedBy = "user")
	protected Set<UserStoreMapping> userStoreMappingSet = new HashSet<>();
	/** 用户和角色映射关系 */
	@OneToMany(mappedBy = "user")
	protected Set<UserRoleMapping> userRoleMappingSet = new HashSet<>();
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