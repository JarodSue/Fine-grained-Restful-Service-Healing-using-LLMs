package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.UserDAO;
import com.example.model.User;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;
    @Autowired
    private PasswordEncoder encoder;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		return user;
	}
	
	
	private User findByUsername(String username) {
	    User user = null;
	    user=userDAO.findByUsername(username);

	    return user;
	}
	
}
