package com.myapp.service;

import java.util.List;

import com.myapp.exception.MovieException;
import com.myapp.model.Movie;
import com.myapp.model.dto.MovieDTO;
import com.myapp.model.dto.ShowInfo;

public interface MovieService {

	public MovieDTO addMovie(MovieDTO movieDTO) throws MovieException;
	
	public List<Movie> getMoviesByName(String movieName) throws MovieException ;
	
	public List<MovieDTO> getMoviesFromDate(String date) throws MovieException;
}
