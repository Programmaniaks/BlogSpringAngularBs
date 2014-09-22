package com.shinzul.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Lists;
import com.shinzul.blog.security.RestAuthenticationEntryPoint;
import com.shinzul.blog.security.RestUsernamePasswordAuthenticationFilter;

@EnableWebMvcSecurity
@ComponentScan("com.shinzul.blog.security")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	
	private UserDetailsService userDetailsService;
	
	public SecurityConfig() {
		userDetailsService = new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username)
					throws UsernameNotFoundException {
				System.out.println("TESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSt");
				if (username.equals("Shinzul")) {
					return new User("Shinzul", "test", Lists.newArrayList(new GrantedAuthorityImpl("USER"), new GrantedAuthorityImpl("ADMIN")));
				}
				return null;
			}
		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		.antMatcher("/services/**")
		.addFilter(new RestUsernamePasswordAuthenticationFilter())
		 .httpBasic()
         .authenticationEntryPoint(restAuthenticationEntryPoint);

	}
	
	  @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService);
	    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/app/**")
		.antMatchers("/img/**")
		.antMatchers("/js/**")
		.antMatchers("/css/**")
		.antMatchers("/lib/**")
		.antMatchers("/partials/**");
	}

	public RestAuthenticationEntryPoint getRestAuthenticationEntryPoint() {
		return restAuthenticationEntryPoint;
	}

	public void setRestAuthenticationEntryPoint(
			RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
	}

}
