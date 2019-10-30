package com.pramati.kart.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pramati.kart.security.JpaUserDetailService;


@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	private DataSource dataSource;


	@Bean
	public PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());

		DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
		delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder(10));
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new JpaUserDetailService();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService())
		.passwordEncoder(passwordEncoder());
	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
