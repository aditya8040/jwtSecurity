package com.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class customUser {
	
	@Id
	private String username;
	
	private String password;
	
	private String number;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	

}
