package com.myapp.service;

import com.myapp.exception.UserException;
import com.myapp.model.User;
import com.myapp.model.dto.UserMobileDTO;
import com.myapp.model.dto.UserPassDTO;

public interface UserService {

	public User registerNewUser(User user) throws UserException ;
	
	public User updateMobileNumber(UserMobileDTO userMobileDTO) throws UserException;
	
	public User updatePassword(UserPassDTO userPassDTO) throws UserException;
}
