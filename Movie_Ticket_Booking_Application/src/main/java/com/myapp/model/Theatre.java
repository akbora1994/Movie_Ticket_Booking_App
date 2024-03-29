package com.myapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer theatreId;
	
	@NotNull(message = "Name cannot be Null")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@JsonIgnore 
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "theatre")
	private List<Screen> screens = new ArrayList<>();
	

}







