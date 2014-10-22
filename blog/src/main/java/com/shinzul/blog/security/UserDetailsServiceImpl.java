package com.shinzul.blog.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shinzul.blog.dao.UserRepository;
import com.shinzul.blog.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User foundUser = getUserRepository().findByUsername(username);
		if (foundUser != null) {
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			foundUser.getRoles().parallelStream().forEach((role) -> { 
				grantedAuths.add(new SimpleGrantedAuthority(role));
			}); 
			org.springframework.security.core.userdetails.User result = 
					new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(), grantedAuths);
	        return result;
		}
		return null;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
