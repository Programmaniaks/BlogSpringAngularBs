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

import com.shinzul.blog.security.UserDetailsServiceImpl;

@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan("com.shinzul.blog.security")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String PROGRAMMANIAK_USER_REALM = "Programmaniaks-Blog";
	private static final String DIGEST_KEY = "4aE!q$6sy_P8è";
	
//	@Autowired
//	private UserAuthenticationProvider authenticationProvider;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		Configuration of username password filter to authenticate
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
//	                return uri.endsWith("/j_security_login");
//	            }
//
//	            return uri.endsWith(request.getContextPath() + LOGIN_URL);
//			}
//		});
//		authenticationFilter.setAuthenticationManager(authenticationManager());
		
		DigestAuthenticationEntryPoint digestEntryPoint = new DigestAuthenticationEntryPoint();
		digestEntryPoint.setRealmName(PROGRAMMANIAK_USER_REALM);
		digestEntryPoint.setKey(DIGEST_KEY);
		
		DigestAuthenticationFilter digestFilter = new DigestAuthenticationFilter();
		digestFilter.setAuthenticationEntryPoint(digestEntryPoint);
		digestFilter.setUserDetailsService(getUserDetailsServiceImpl());
		
		http.antMatcher("/services/**")
			// Configure usage of Http basic authentication (need to be force to préemptive to skip Digest primary entry point)
			.httpBasic()
			// Configure Digest as primary authentication entryPoint
			.authenticationEntryPoint(digestEntryPoint)
			.and()
			// Configure Digest filter to support authentication from entry point
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
//		Customize UserDetail only on AuthenticationProvider
		auth.userDetailsService(getUserDetailsServiceImpl());
		
//		Override AuthenticationProvider
//		auth.authenticationProvider(getAuthenticationProvider());
		
//		Configure inMemory user database
//		auth.inMemoryAuthentication().withUser("user").password("password")
//				.roles("USER").and().withUser("admin").password("password")
//				.roles("USER", "ADMIN");
		
	}

//	public UserAuthenticationProvider getAuthenticationProvider() {
//		return authenticationProvider;
//	}
//
//	public void setAuthenticationProvider(
//			UserAuthenticationProvider authenticationProvider) {
//		this.authenticationProvider = authenticationProvider;
//	}

	public UserDetailsServiceImpl getUserDetailsServiceImpl() {
		return userDetailsServiceImpl;
	}

	public void setUserDetailsServiceImpl(
			UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}



}
