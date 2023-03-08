package com.myapp.service;

import java.util.List;

import com.myapp.exception.SeatLockException;
import com.myapp.model.Seat;
import com.myapp.model.Shows;

public interface SeatLockService {

	public boolean lockSeats(Shows shows,List<Seat> seats,Integer userId) throws SeatLockException;
	
	public List<Seat> getAllLockedSeats(Shows show) throws SeatLockException;
	
	public boolean validateLock(Shows shows) throws SeatLockException;

}
