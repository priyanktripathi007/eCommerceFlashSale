package com.pramati.kart.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	DataSource dataSource;

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("12345");
		return converter;
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(this.dataSource);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(this.authenticationManager)
		.accessTokenConverter(jwtAccessTokenConverter())
		.userDetailsService(this.userDetailsService)
		.tokenStore(tokenStore());
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.passwordEncoder(this.passwordEncoder).tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}
}
