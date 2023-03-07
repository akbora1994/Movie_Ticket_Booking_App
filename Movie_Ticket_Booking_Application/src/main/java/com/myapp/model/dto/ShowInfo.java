package com.myapp.model.dto;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import com.myapp.model.Address;
import com.myapp.model.Movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowInfo {

	private Integer showId;
	
	private LocalDateTime showTiming;
	
	private Integer durationInSeconds;
	
	private Integer screenId;
	private String screenName;
	
	private Integer noOfSeats;
	private Integer availableSeats;
	
	private Integer theatreId;
	private String theatreName;
	
	private Address address;
	
	private Movie movie;
}
