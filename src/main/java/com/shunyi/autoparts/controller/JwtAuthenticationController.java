package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.config.JwtTokenUtil;
import com.shunyi.autoparts.model.dto.JwtRequest;
import com.shunyi.autoparts.model.dto.JwtResponse;
import com.shunyi.autoparts.model.dto.UserDTO;
import com.shunyi.autoparts.service.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @description JWT认证控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {
	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch(DisabledException e) {
			return ResponseEntity.ok(new JwtResponse("用户已禁用"));
		} catch(BadCredentialsException e) {
			return ResponseEntity.ok(new JwtResponse("用户名或密码错误"));
		}
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(
				token,
				userDetails.getUsername(),
				new Date()));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws DisabledException, BadCredentialsException {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}