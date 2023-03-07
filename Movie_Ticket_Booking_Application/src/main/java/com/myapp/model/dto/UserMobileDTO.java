package com.myapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMobileDTO {

	private Integer userId;
	
	private String currentPassword;
	
	private String newMobileNumber;
	
}
