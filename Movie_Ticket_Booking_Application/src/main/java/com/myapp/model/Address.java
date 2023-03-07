package com.myapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Integer addressId;
	
	@NotNull(message = "streetName cannot be Null")
	private String streetName;
	
	@NotNull(message = "city cannot be Null")
	private String city;
	
	@NotNull(message = "pincode cannot be Null")
	@Size(min = 6,max = 6,message = "pincode must be of size 6")
	private String pincode;
	
}




