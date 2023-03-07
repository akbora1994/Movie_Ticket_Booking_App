package com.myapp.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.exception.MovieException;
import com.myapp.model.Movie;
import com.myapp.model.dto.MovieDTO;
import com.myapp.repository.CastRepo;
import com.myapp.repository.MovieRepo;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired 
	private CastRepo castRepo;
	
	@Override
	public MovieDTO addMovie(MovieDTO movieDTO) throws MovieException {
		
		Movie movie = new Movie();
			
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		String movieDTODate = movieDTO.getReleasingDate();
		LocalDate movieReleasingDate = LocalDate.parse(movieDTODate, dtf);
		
		if(movieReleasingDate.isBefore(LocalDate.now())) 
			throw new MovieException("Date cannot be in Past") ;
		
		BeanUtils.copyProperties(movieDTO, movie);
		movie.setReleaseDate(movieReleasingDate);
		
		Movie registerdMovie = movieRepo.save(movie);
		BeanUtils.copyProperties(registerdMovie,movieDTO);
		movieDTO.setReleasingDate(movieDTODate);
		
		return movieDTO; 
	}

	@Override
	public List<Movie> getMoviesByName(String movieName) throws MovieException {
		
		List<Movie> movieList = movieRepo.findByName(movieName);

		if(movieList.size() == 0)
			throw new MovieException("Movie Not found with Name: "+movieName) ;
		
		return movieList;
	}

	@Override
	public List<MovieDTO> getMoviesFromDate(String date) throws MovieException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy") ;
		LocalDate dateFrom = LocalDate.parse(date,dtf);
		
		List<Movie> moviesList = movieRepo.getMoviesFromDate(dateFrom);
		
		if(moviesList.size() == 0) 
			throw new MovieException("No movies found by Date: "+date) ;
		
		List<MovieDTO> moviesDTOList = this.copyPropertiestToDTO(moviesList);
		
		return moviesDTOList;
	}
	
	public List<MovieDTO> copyPropertiestToDTO(List<Movie> movies) {
		
		List<MovieDTO> moviesList = new ArrayList<>();
		
		for(Movie movie : movies) {
			MovieDTO movieDTO = new MovieDTO();
			BeanUtils.copyProperties(movie, movieDTO);
			moviesList.add(movieDTO);
		}
		
		return moviesList;
	}

}













