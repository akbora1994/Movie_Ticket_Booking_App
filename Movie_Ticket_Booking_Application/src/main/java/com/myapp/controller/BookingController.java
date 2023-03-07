package com.myapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.exception.BookingException;
import com.myapp.exception.SeatLockException;
import com.myapp.exception.SeatPermantlyUnavailableException;
import com.myapp.exception.SeatTemporaryUnavailableException;
import com.myapp.model.dto.BookingDTO;
import com.myapp.model.dto.BookingInfoDTO;
import com.myapp.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingSer;
	
	@PostMapping("/bookings/")
	public ResponseEntity<BookingDTO> bookMovieHandler(@Valid @RequestParam("userId") Integer userId, 
														@RequestParam("showId") Integer showId,
														@RequestBody List<Integer> listOfSeatId) 
														throws BookingException, 
														SeatLockException, 
														SeatPermantlyUnavailableException, 
														SeatTemporaryUnavailableException {
		
		BookingDTO bookingDTO =  bookingSer.bookAMovie(userId, showId, listOfSeatId);
		return new ResponseEntity<BookingDTO>(bookingDTO,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/bookings/conformed")
	public ResponseEntity<BookingDTO> conformBookingHandler(@RequestParam("bookingId") Integer bookingId,
															@RequestParam("userId") Integer userId) throws BookingException, SeatLockException {
		
		BookingDTO bookingDTO = bookingSer.conformBooking(bookingId, userId);
		
		return new ResponseEntity<BookingDTO>(bookingDTO,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/bookings/")
	public ResponseEntity<BookingDTO> getBookingDetailsByIdHankler(@RequestParam("bookingId") Integer bookingId) throws BookingException {
		
		BookingDTO bookingDTO =  bookingSer.getBookingDetails(bookingId);
		
		return new ResponseEntity<BookingDTO>(bookingDTO,HttpStatus.OK);
		
	}
	
	@GetMapping("/bookings/all/")
	public ResponseEntity<List<BookingInfoDTO>> getAllBookingOfUserHandler(@RequestParam("userId") Integer userId) throws BookingException {
		
		List<BookingInfoDTO> bookingList = bookingSer.getAllBookingOfUser(userId);
	
		return new ResponseEntity<List<BookingInfoDTO>>(bookingList,HttpStatus.OK); 
	}
	
	
	
	
	
	
}











