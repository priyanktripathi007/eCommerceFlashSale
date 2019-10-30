package com.pramati.kart.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.pramati.kart.entities.CustomUserDetails;
import com.pramati.kart.entities.User;
import com.pramati.kart.repository.UserRepository;

public class JpaUserDetailService implements UserDetailsManager{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		return user.map(CustomUserDetails::new).get();
	}

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
