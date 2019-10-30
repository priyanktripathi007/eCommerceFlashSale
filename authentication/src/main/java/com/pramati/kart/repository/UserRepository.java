package com.pramati.kart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramati.kart.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public  Optional<User>  findByUsername(String username);
}
