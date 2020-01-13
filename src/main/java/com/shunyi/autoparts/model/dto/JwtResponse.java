package com.shunyi.autoparts.model.dto;


import java.io.Serializable;
import java.util.Date;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String token;
	private String username;
	private Date lastLoginDate;
	private String error;

	public JwtResponse(String token, String username, Date lastLoginDate) {
		this.token = token;
		this.username = username;
		this.lastLoginDate = lastLoginDate;
	}

	public JwtResponse(String error) {
		this.error = error;
	}

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}