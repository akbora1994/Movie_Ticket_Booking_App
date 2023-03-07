package com.myapp.service;


import java.util.List;

import com.myapp.exception.TheatreException;
import com.myapp.model.Theatre;
import com.myapp.model.dto.TheatreDTO;

public interface TheatreService {

	public Theatre registerTheater(Theatre theatre) throws TheatreException;
	
	public List<TheatreDTO> findTheaterByCityORPincode(String query) throws TheatreException;
	
	public List<TheatreDTO> findTheatreByName(String theatreName) throws TheatreException;
}
