package com.myapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.exception.UserException;
import com.myapp.model.User;
import com.myapp.model.dto.UserMobileDTO;
import com.myapp.model.dto.UserPassDTO;
import com.myapp.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService ;
	
	
	@PostMapping("/users")
	public ResponseEntity<User> registerUserHandler(@Valid @RequestBody User user) throws UserException {
		
		User registerUser = userService.registerNewUser(user) ;
		
		return new ResponseEntity<User>(registerUser,HttpStatus.CREATED) ;
	}
	
	@PatchMapping("/users/update/pass")
	public ResponseEntity<User> updateUserPasswordHandler(@Valid @RequestBody UserPassDTO userPassDTO) throws UserException {
		
		User updatedUser = userService.updatePassword(userPassDTO);
		return new ResponseEntity<User>(updatedUser,HttpStatus.CREATED) ;
	}
	
	@PatchMapping("/users/update/mobile")
	public ResponseEntity<User> updateUserMobileHandler(@Valid @RequestBody UserMobileDTO userMobileDTO) throws UserException {
		
		User updatedUser = userService.updateMobileNumber(userMobileDTO);
		return new ResponseEntity<User>(updatedUser,HttpStatus.CREATED) ;
	}
	
	
	
}

