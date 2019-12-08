package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private long id;

	/** 用户名 */
	private String username;

	/** 加密的密码 */
	@JsonIgnore
	private String password;

	/** 是否激活 */
	private Boolean enabled;

	/** 用户店铺映射关系 */
	@OneToMany(mappedBy = "user")
	protected Set<UserShopMapping> userShopMappingSet = new HashSet<>();

	public User() {}

	public User(String username, String password, Boolean enabled, Set<UserShopMapping> userShopMappingSet) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userShopMappingSet = userShopMappingSet;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Boolean getEnabled() {
		return enabled;
	}

	public Set<UserShopMapping> getUserShopMappingSet() {
		return userShopMappingSet;
	}

	public void setUserShopMappingSet(Set<UserShopMapping> userShopMappingSet) {
		this.userShopMappingSet = userShopMappingSet;
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