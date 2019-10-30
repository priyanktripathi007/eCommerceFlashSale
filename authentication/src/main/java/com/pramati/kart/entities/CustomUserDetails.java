package com.pramati.kart.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1007658379232235181L;

	private String username;
	private String password;
	private String email;
	private int active;
	private List<GrantedAuthority> authorities;

	public CustomUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email=user.getEmail();
		this.active = user.getActive();
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRoles().getName());
		this.authorities = Arrays.asList(grantedAuthority);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active==0?true:false;
	}
}
