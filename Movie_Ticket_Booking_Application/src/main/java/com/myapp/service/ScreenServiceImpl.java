package com.myapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.exception.ScreenException;
import com.myapp.model.Screen;
import com.myapp.model.Seat;
import com.myapp.model.Theatre;
import com.myapp.repository.ScreenRepo;
import com.myapp.repository.SeatRepo;
import com.myapp.repository.TheatreRepo;

@Service
public class ScreenServiceImpl implements ScreenService {
	
	@Autowired
	private ScreenRepo screenRepo;
	
	@Autowired 
	private TheatreRepo theatreRepo;
	
	@Autowired
	private SeatRepo seatRepo;
	
	@Override
	public Screen addScreenToTheater(Integer theaterId, String screenName) throws ScreenException {
		
		Optional<Theatre> theatreOpt =   theatreRepo.findById(theaterId) ;
		
		if(theatreOpt.isEmpty()) throw new ScreenException("Invalid TheatreID!") ;
		
		Theatre theatre = theatreOpt.get();
		
		for(Screen presentScreen : theatre.getScreens()) {
			if(presentScreen.getName().equals(screenName)) {
				throw new ScreenException("theatreId "+theaterId +" screen name "+screenName +" is already Present", new ScreenException()) ;
			}
		}
		
		Screen screen = new Screen();
		screen.setName(screenName);
		screen.setTheatre(theatre);
		
		Screen savedScreen = screenRepo.save(screen);
		
		theatre.getScreens().add(savedScreen) ;
		theatreRepo.save(theatre);
		
		return savedScreen;
	}

	@Override
	public Screen addSeatsToScreen(Integer rows, Integer cols,Integer screenId,Double seatPrice) throws ScreenException {

		Screen screen = screenRepo.findById(screenId).orElseThrow(() -> new ScreenException("Invalid ScreenId " +screenId)) ;
		
		if(screen.getNoOfSeats() != null ) throw new ScreenException("You have already added Seats To this Screen...") ;
		
		List<Seat> listOfSeats = screen.getSeats();
		
		for(int i = 1; i <= rows; i++) {
			for(int j = 1; j <= cols; j++) {
				
				Seat registeredSeat = seatRepo.save(new Seat(i, j)) ;
				listOfSeats.add(registeredSeat) ;
				
			}
		}
		
		screen.setNoOfSeats(rows*cols);
		screen.setSeatRows(rows);
		screen.setSeatCols(cols);
		screen.setSeatPrice(seatPrice);
		
		return screenRepo.save(screen);
			
	}

}























