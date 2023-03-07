package com.myapp.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.myapp.model.Cast;
import com.myapp.model.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

	
	private Integer movieId;
	
	@NotNull(message = "movieName cannot be Null")
	private String name;
	
	@NotNull(message = "genre cannot be Null")
	private String genre;
	
	@NotNull(message = "duration cannot be Null")
	private String duration;
	
	private boolean isIn2D;
	private boolean isIn3d;
	
	@NotNull(message = "releasingDate cannot be Null")
	private String releasingDate;
	
	private List<Cast> casts = new ArrayList<>() ;
	
	private Rating ratingInfo;
	private String posterURL;
}










