package com.myapp.model.dto;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.myapp.model.Movie;
import com.myapp.model.Screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowsDTO {

	private Integer showId;
	
	@NotNull(message = "showDateTime cannot be Null")
	private String showDateTime;
	
	@Min(value = 1,message = "Duration should be min 1")
	private Integer durationInSeconds;
	
	private Integer ScreenId;
	private Integer movieId;
	
	private Integer totalSeats;
	
	private Integer availableSeats;
}










