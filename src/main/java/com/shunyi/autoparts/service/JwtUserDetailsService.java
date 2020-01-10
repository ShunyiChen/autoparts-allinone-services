package com.shunyi.autoparts.service;

import com.shunyi.autoparts.dao.UserDao;
import com.shunyi.autoparts.model.User;
import com.shunyi.autoparts.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities("admin"));
	}


	private List<SimpleGrantedAuthority> getAuthorities(String role) {
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		//you can also add different roles here
		//for example, the user is also an admin of the site, then you can add ROLE_ADMIN
		//so that he can view pages that are ROLE_ADMIN specific
		if (role != null && role.trim().length() > 0) {
			if (role.equals("admin")) {
				authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
		}
		return authList;
	}

	public User save(UserDTO user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}

}