package com.myapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.enums.BookingStatus;
import com.myapp.exception.BookingException;
import com.myapp.exception.SeatLockException;
import com.myapp.exception.SeatPermantlyUnavailableException;
import com.myapp.exception.SeatTemporaryUnavailableException;
import com.myapp.model.Booking;
import com.myapp.model.Seat;
import com.myapp.model.SeatLock;
import com.myapp.model.Shows;
import com.myapp.model.User;
import com.myapp.model.dto.BookingDTO;
import com.myapp.model.dto.BookingInfoDTO;
import com.myapp.repository.BookingRepo;
import com.myapp.repository.ScreenRepo;
import com.myapp.repository.SeatLockRepo;
import com.myapp.repository.SeatRepo;
import com.myapp.repository.ShowRepo;
import com.myapp.repository.UserRepo;


@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Autowired
	private ScreenRepo screenRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShowRepo showRepo;
	
	@Autowired
	private SeatLockService seatLockService;
	
	@Autowired 
	private SeatRepo seatRepo;
	
	@Autowired 
	private SeatLockRepo seatLockRepo;
	
	@Override
	public BookingDTO bookAMovie(Integer userId, Integer showId,List<Integer> listOfSeatId) throws BookingException, SeatLockException, SeatPermantlyUnavailableException, SeatTemporaryUnavailableException {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new BookingException("Invalid userID: "+userId)) ;
		Shows show = showRepo.findById(showId).orElseThrow(() -> new BookingException("Invalid showID: "+showId)) ;
		
		List<Seat> seatsToLocked = new ArrayList<>();
		
		for(Integer seatId : listOfSeatId) {
			Seat singleSeat = seatRepo.findById(seatId).orElseThrow(() -> new BookingException("Invalid Seat ID "+seatId) ) ;
			seatsToLocked.add(singleSeat);
 		}
		
		List<Seat> boookedSeats = this.getBookedSeats(show);
		
		for(Seat singleSeat : boookedSeats) {
			if(listOfSeatId.contains(singleSeat.getSeatId())) 
				throw new SeatPermantlyUnavailableException("Seat is permanantly booked! You can book another seats") ;
		}
		
		boolean response = seatLockService.lockSeats(show, seatsToLocked, userId);
		
		if(!response)
			throw new BookingException("Seat is Not available!");
		
		show.setAvailableSeats(show.getAvailableSeats() - listOfSeatId.size());	
		showRepo.save(show) ;													
		
		Booking booking = new Booking(userId, show, BookingStatus.Created,show.getScreen().getSeatPrice() * listOfSeatId.size(),LocalDateTime.now()) ;
		
		bookingRepo.save(booking) ;
		
		BookingDTO bookingDTO = new BookingDTO();
		BeanUtils.copyProperties(booking, bookingDTO);
		
		return bookingDTO;
	}

	
	@Override
	public BookingDTO conformBooking(Integer bookingId, Integer userId) throws BookingException, SeatLockException {
		
		
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(()-> new BookingException("Invalid bookingID:"+bookingId));
		
		
		if(!booking.getUserId().equals(userId)) {
			throw new BookingException("Bad Request!");
		}
		
		User user = userRepo.findById(userId).orElseThrow(()-> new BookingException("Invalid UserID")) ;
		
		seatLockService.validateLock(booking.getShows()) ;
		
		List<SeatLock> seatLocks = seatLockRepo.findByLockedByUser(booking.getUserId()) ;
		
		if(seatLocks.size() == 0) 
			throw new BookingException("Your Time for Booking is Expired!");
		
		List<Integer> seatNoList = new ArrayList<>();
		
		for(SeatLock seatLock : seatLocks) {
			
			seatNoList.add(seatLock.getSeatId()) ;
			seatLockRepo.deleteById(seatLock.getSeatLockId());
			
			Seat seat = seatRepo.findById(seatLock.getSeatId()).orElseThrow(()-> new BookingException("Invalid SeatId"));
			booking.getSeats().add(seat);
		}
		
		booking.setBookingStatus(BookingStatus.Conformed);
		booking = bookingRepo.save(booking) ;
		
		BookingDTO bookingDTO = new BookingDTO();
		
		BeanUtils.copyProperties(booking, bookingDTO);
		bookingDTO.setSeatsId(seatNoList);
		
		return bookingDTO;
	}
	
	@Override
	public List<Seat> getBookedSeats(Shows shows) throws BookingException {
		
		List<Booking> bookings = bookingRepo.getBookedSeats(shows) ;
		List<Seat> bookedSeats = new ArrayList<>();

		for(Booking singleBooking : bookings) bookedSeats.addAll(singleBooking.getSeats()) ;

		return bookedSeats;
	}


	@Override
	public BookingDTO getBookingDetails(Integer bookingId) throws BookingException {
		
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(()-> new BookingException("Invalid BookingID: "+bookingId)) ;
		
		BookingDTO bookingDTO = new BookingDTO();
		BeanUtils.copyProperties(booking, bookingDTO);
		
		for(Seat seat : booking.getSeats()) {
			bookingDTO.getSeatsId().add(seat.getSeatId()) ;
		}
		
		return bookingDTO;
	}

	@Override
	public List<BookingInfoDTO> getAllBookingOfUser(Integer userId) throws BookingException {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new BookingException("Invalid User Id : "+userId)) ;
		
		List<Booking> bookings = bookingRepo.getAllBookingByUserId(userId);
		
		List<BookingInfoDTO> infoList = new ArrayList<>();
		
		for(Booking booking : bookings) {
			BookingInfoDTO infoDTO = new BookingInfoDTO(booking.getBookingId(), booking.getUserId(), booking.getShows().
							getShowId(), booking.getShows().getScreen().getScreenId(), booking.getShows().getScreen().getName(), 
							booking.getShows().getScreen().getTheatre().getTheatreId(), booking.getShows().getScreen().getTheatre().getName(), 
							booking.getShows().getScreen().getTheatre().getAddress(), booking.getShows().getMovie().getMovieId(), 
							booking.getShows().getMovie().getName(), booking.getShows().getMovie().getPosterURL(), 
							booking.getSeats(), booking.getBookingStatus(),booking.getTotalBillAmount(),booking.getShows().getShowTiming() )  ;
			
			infoList.add(infoDTO);
		}
		
		return infoList;
	}
	

}














