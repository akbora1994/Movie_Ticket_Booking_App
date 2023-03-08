package com.myapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.enums.BookingStatus;
import com.myapp.exception.SeatLockException;
import com.myapp.model.Booking;
import com.myapp.model.Seat;
import com.myapp.model.SeatLock;
import com.myapp.model.Shows;
import com.myapp.repository.BookingRepo;
import com.myapp.repository.SeatLockRepo;
import com.myapp.repository.SeatRepo;
import com.myapp.repository.ShowRepo;

@Service
public class SeatLockServiceImpl implements SeatLockService {
	
	final private Integer lockTimeInSeconds = 300 ;
	
	@Autowired
	private SeatLockRepo seatLockRepo;
	
	@Autowired
	private SeatRepo seatRepo;
	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Autowired
	private ShowRepo showRepo;
	
	
	@Override
	public boolean lockSeats(Shows shows, List<Seat> seats, Integer userId) throws SeatLockException {
		
		List<Seat> lockedSeats = this.getAllLockedSeats(shows);
		
		for(Seat singleSeat : seats) {
			if(lockedSeats.contains(singleSeat)) 
				throw new SeatLockException("This Seat is Temporarily Locked! You can choose another seat..") ;
		}	
		
		for(Seat singleSeat : seats) {
			System.out.println("Inside_+++");
			seatLockRepo.save(new SeatLock(singleSeat.getSeatId(), shows.getShowId(), lockTimeInSeconds, LocalDateTime.now(), userId));
		}
		
		
		return true;
	}
	
	@Override
	public List<Seat> getAllLockedSeats(Shows show) throws SeatLockException {
		
		this.validateLock(show);
		List<Integer> lockedSeatIds = seatLockRepo.getLockedSeats(show.getShowId());
	 	
	 	List<Seat> lockedSeats = new ArrayList<>();
	 	for(Integer seatId : lockedSeatIds) {
	 		lockedSeats.add(seatRepo.findById(seatId).get() );
	 	}
		
	 	return lockedSeats;
	}


	@Override
	public boolean validateLock(Shows shows) throws SeatLockException {
		
		List<SeatLock> seatLocks = seatLockRepo.findByShowsId(shows.getShowId()) ;
		
		boolean flag = true;
		
		for(SeatLock seatLock : seatLocks) {
			
			LocalDateTime dateTimeNow = LocalDateTime.now(); 
			LocalDateTime expiryTime = seatLock.getDateTime().plusSeconds(lockTimeInSeconds) ;
				
			if(dateTimeNow.isAfter(expiryTime)) {

				seatLockRepo.delete(seatLock);
				shows.setAvailableSeats(shows.getAvailableSeats()+1);	
				
				List<Booking> expiredBookings =  bookingRepo.getBookingByUserShowAndStatus(seatLock.getLockedByUser(), shows, BookingStatus.Created) ;
				
				for(Booking expireBooking : expiredBookings) {
					expireBooking.setBookingStatus(BookingStatus.Expired) ;
					bookingRepo.save(expireBooking);
				}
				
				flag = false;
			}
		}
		showRepo.save(shows) ;
		return flag;
	}

}

















