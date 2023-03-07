package com.myapp.service;

import java.util.List;

import com.myapp.exception.BookingException;
import com.myapp.exception.SeatLockException;
import com.myapp.exception.SeatPermantlyUnavailableException;
import com.myapp.exception.SeatTemporaryUnavailableException;
import com.myapp.model.Seat;
import com.myapp.model.Shows;
import com.myapp.model.dto.BookingDTO;
import com.myapp.model.dto.BookingInfoDTO;

public interface BookingService {

	public BookingDTO bookAMovie(Integer userId,Integer showId,List<Integer> listOfSeatId) throws BookingException, SeatLockException, SeatPermantlyUnavailableException, SeatTemporaryUnavailableException;

	public BookingDTO conformBooking(Integer bookingId,Integer userId) throws BookingException, SeatLockException;
	
	public List<Seat> getBookedSeats(Shows shows) throws BookingException ;
	
	public BookingDTO getBookingDetails(Integer bookingId) throws BookingException;
	
	public List<BookingInfoDTO> getAllBookingOfUser(Integer userId) throws BookingException; 
}
