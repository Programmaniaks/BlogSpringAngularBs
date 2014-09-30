package com.shinzul.blog.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.shinzul.blog.dao.UserRepository;
import com.shinzul.blog.entity.User;



@Service
public class UserAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String principal = null;
		String credential = null;
		if (authentication.getPrincipal() instanceof String) {
			principal = (String) authentication.getPrincipal();
		}
		if (authentication.getCredentials() instanceof String) {
			credential = (String) authentication.getCredentials();
		}
		User foundUser = getUserRepository().findByUsernameAndPassword(principal, credential);
		if (foundUser != null) {
			
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			foundUser.getRoles().parallelStream().forEach((role) -> { 
				grantedAuths.add(new SimpleGrantedAuthority(role));
			}); 
			
	        return new UsernamePasswordAuthenticationToken(principal, credential, grantedAuths);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		if (UsernamePasswordAuthenticationToken.class.equals(authentication)) {
			return true;
		}
		return false;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	

}
