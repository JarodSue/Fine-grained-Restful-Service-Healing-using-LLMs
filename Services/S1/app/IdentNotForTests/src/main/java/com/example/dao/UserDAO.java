package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.*;


	public interface UserDAO extends JpaRepository<User, Integer> {
		User findById(int id);
		User findByUsername(String username);
                boolean existsByUsername(String username);
	}


