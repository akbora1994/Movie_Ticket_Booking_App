package com.myapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer castId;
	
	private String role; 
	
	private String name;
	
}
