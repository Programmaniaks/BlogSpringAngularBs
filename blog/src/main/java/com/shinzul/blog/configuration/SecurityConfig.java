package com.shinzul.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.shinzul.blog.security.RestUsernamePasswordAuthenticationFilter;

@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan("com.shinzul.blog.security")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/services/**")
			.addFilter(new RestUsernamePasswordAuthenticationFilter());
		// .httpBasic()
		// .authenticationEntryPoint(restAuthenticationEntryPoint);

	}

	@Autowired
	public void registerSharedAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password")
				.roles("USER").and().withUser("admin").password("password")
				.roles("USER", "ADMIN");
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/app/**").antMatchers("/img/**")
//				.antMatchers("/js/**").antMatchers("/css/**")
//				.antMatchers("/lib/**").antMatchers("/partials/**");
//	}

}
