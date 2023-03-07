package com.myapp.service;

import java.util.List;

import com.myapp.exception.BookingException;
import com.myapp.exception.MovieException;
import com.myapp.exception.SeatLockException;
import com.myapp.exception.ShowExcepion;
import com.myapp.model.Seat;
import com.myapp.model.dto.ShowInfo;
import com.myapp.model.dto.ShowsDTO;

public interface ShowsService {

	public ShowsDTO addShowsToScreen(ShowsDTO showsDTO) throws ShowExcepion;
	
	public List<Seat> getAvailableSeats(Integer showId) throws ShowExcepion, BookingException, SeatLockException;
	
	public List<ShowInfo> getShowsByMovieId(Integer movieId) throws ShowExcepion ;
}
