package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.model.User;

public interface UserRepo  extends JpaRepository<User, Integer>  {

	public User findByMobileNumber(String mobileNumber) ;
	
}
