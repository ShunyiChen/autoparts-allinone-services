package com.shunyi.autoparts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description 系统用户DTO
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDTO {
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
}