package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface customUserDao extends JpaRepository<customUser, String> {
	
	
	customUserDao loadCustomUserByUsername(String username); 

}
