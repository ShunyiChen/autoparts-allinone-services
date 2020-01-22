package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** 用户 */
@Entity
@Table(name = "users")
public class User {
	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 用户名 */
	private String username;

	/** 加密的密码 */
	private String password;

	/** 是否激活 */
	private Boolean enabled;

	/** 用户和店铺映射关系 */
	@OneToMany(mappedBy = "user")
	protected Set<UserShopMapping> userShopMappingSet = new HashSet<>();

	/** 用户和角色映射关系 */
	@OneToMany(mappedBy = "user")
	protected Set<UserRoleMapping> userRoleMappingSet = new HashSet<>();

	public User() {}

	public User(String username, String password, Boolean enabled, Set<UserShopMapping> userShopMappingSet, Set<UserRoleMapping> userRoleMappingSet) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userShopMappingSet = userShopMappingSet;
		this.userRoleMappingSet = userRoleMappingSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserShopMapping> getUserShopMappingSet() {
		return userShopMappingSet;
	}

	public void setUserShopMappingSet(Set<UserShopMapping> userShopMappingSet) {
		this.userShopMappingSet = userShopMappingSet;
	}

	public Set<UserRoleMapping> getUserRoleMappingSet() {
		return userRoleMappingSet;
	}

	public void setUserRoleMappingSet(Set<UserRoleMapping> userRoleMappingSet) {
		this.userRoleMappingSet = userRoleMappingSet;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", enabled=" + enabled +
				", userShopMappingSet=" + userShopMappingSet +
				'}';
	}
}