package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/** 登录用户/操作者/审核人 */
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
	private Department department;

	public User() {}

	public User(String username, String password, Boolean enabled, Department department) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.department = department;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Boolean getEnabled() {
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
				", department=" + department +
				'}';
	}
}