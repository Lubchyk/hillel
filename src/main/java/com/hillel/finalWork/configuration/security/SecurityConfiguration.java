package com.hillel.finalWork.configuration.security;

import com.hillel.finalWork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/admin").access("hasRole('ADMIN')")
				.antMatchers("/manager").access("hasRole('MANAGER') or hasRole('ADMIN')")
				.antMatchers("/user").access("hasRole('CUSTOMER') or hasRole('MANAGER') or hasRole('ADMIN')")
				.anyRequest().authenticated()
				.and().formLogin()
				.loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
				.and().csrf().disable().authorizeRequests()
				.and().exceptionHandling().accessDeniedPage("/breakpoint")
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
	}
}
