package com.myapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPassDTO {
	
	private Integer userId;
	
	private String newPassword;
	
	private String currentPassword;
	
}
