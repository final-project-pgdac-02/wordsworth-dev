package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmailAndPassword(String email,String password);

	
}

