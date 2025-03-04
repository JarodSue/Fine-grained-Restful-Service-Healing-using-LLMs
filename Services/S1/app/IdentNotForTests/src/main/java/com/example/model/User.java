package com.example.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table

	
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private ArrayList<String> authorityNames = new ArrayList<String>();
	private String username;

	
	@JsonIgnore
	private String password;	
	
	public User() {
		
		this.authorityNames.add("JWT_USER");
	}
	public User(String username) {
		this.username=username;
		this.authorityNames.add("JWT_USER");
	}
	
	public User( String username,  String password) {
		this.username=username;
		this.password=password;
		this.authorityNames.add("JWT_USER");
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAuthorityNames() {
		return authorityNames;
	}
	public void setAuthorityNames(ArrayList<String> authorityNames) {
		this.authorityNames = authorityNames;
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
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = authorityNames
				.stream()
				.map(auth -> new SimpleGrantedAuthority(auth))
				.collect(Collectors.toList());

		return list;
	}
	
}
