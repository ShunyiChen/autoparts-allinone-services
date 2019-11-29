package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/** 用户 */
@Entity
@Table(name = "users")
public class User {
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
	@ManyToOne
	@JoinColumn(name = "department_id",
			foreignKey = @ForeignKey(name = "DEPARTMENT_ID_FK")
	)
	private Shop shop;

	public User() {}

	public User(String username, String password, Boolean enabled, Shop shop) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.shop = shop;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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
				", shop=" + shop +
				'}';
	}
}