package com.myapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotNull(message = "Name cannot be Null")
	private String name;
	
	@Pattern(regexp = "[7-9][0-9]{9}",message = "Mobile number should start with 7-9 and should be of size 10")
	private String mobileNumber;
 
	@NotNull
	private String password;
	
	@Email(message = "Enter a valid emailId")
	private String email;
	
	private String role;
	
}










