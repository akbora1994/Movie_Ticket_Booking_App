package com.myapp.service;

import com.myapp.exception.ScreenException;
import com.myapp.model.Screen;

public interface ScreenService {

	public Screen addScreenToTheater(Integer theaterId,String screenName) throws ScreenException ;
	
	public Screen addSeatsToScreen(Integer rows,Integer cols,Integer screenId,Double seatPrice) throws ScreenException ;
}
