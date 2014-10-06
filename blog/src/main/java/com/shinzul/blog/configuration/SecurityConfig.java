package com.shinzul.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import com.shinzul.blog.security.UserAuthenticationProvider;
import com.shinzul.blog.security.UserDetailsServiceImpl;

@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan("com.shinzul.blog.security")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserAuthenticationProvider authenticationProvider;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		UsernamePasswordAuthenticationFilter authenticationFilter = new UsernamePasswordAuthenticationFilter();
//		authenticationFilter.setPostOnly(true);
//		authenticationFilter.setUsernameParameter("username");
//		authenticationFilter.setPasswordParameter("password");
//		authenticationFilter.setRequiresAuthenticationRequestMatcher(new RequestMatcher() {
//			
//			@Override
//			public boolean matches(HttpServletRequest request) {
//				String uri = request.getRequestURI();
//	            int pathParamIndex = uri.indexOf(';');
//
//	            if (pathParamIndex > 0) {
//	                uri = uri.substring(0, pathParamIndex);
//	            }
//
//	            if ("".equals(request.getContextPath())) {
//	                return uri.endsWith(LOGIN_URL);
//	            }
//
//	            return uri.endsWith(request.getContextPath() + LOGIN_URL);
//			}
//		});
//		authenticationFilter.setAuthenticationManager(authenticationManager());
		
		DigestAuthenticationEntryPoint digestEntryPoint = new DigestAuthenticationEntryPoint();
		digestEntryPoint.setRealmName("FTE-TEST");
		digestEntryPoint.setKey("acegi");
		DigestAuthenticationFilter digestFilter = new DigestAuthenticationFilter();
		digestFilter.setAuthenticationEntryPoint(digestEntryPoint);
		digestFilter.setUserDetailsService(getUserDetailsServiceImpl());
		
		http.antMatcher("/services/**")
			.httpBasic()
			.authenticationEntryPoint(digestEntryPoint)
			.and()
			.addFilterAfter(digestFilter, BasicAuthenticationFilter.class)
			;

	}
	
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/app/**").antMatchers("/img/**")
//				.antMatchers("/js/**").antMatchers("/css/**")
//				.antMatchers("/lib/**").antMatchers("/partials/**");
//	}

	@Autowired
	public void registerSharedAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(getUserDetailsServiceImpl());
//		auth.authenticationProvider(getAuthenticationProvider());
//		auth.inMemoryAuthentication().withUser("user").password("password")
//				.roles("USER").and().withUser("admin").password("password")
//				.roles("USER", "ADMIN");
		
	}

	public UserAuthenticationProvider getAuthenticationProvider() {
		return authenticationProvider;
	}

	public void setAuthenticationProvider(
			UserAuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	public UserDetailsServiceImpl getUserDetailsServiceImpl() {
		return userDetailsServiceImpl;
	}

	public void setUserDetailsServiceImpl(
			UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}



}
