package com.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.exception.TheatreException;
import com.myapp.model.Screen;
import com.myapp.model.Theatre;
import com.myapp.model.dto.TheatreDTO;
import com.myapp.service.TheatreService;

@RestController
public class TheatreController {

	@Autowired
	private TheatreService theatreService;
	
	@PostMapping("/theatres/")
	public ResponseEntity<Theatre> registerTheatreHandler(@RequestBody Theatre theatre) throws TheatreException {
		
		Theatre registerTheatre =  theatreService.registerTheater(theatre);
		return new ResponseEntity<Theatre>(registerTheatre,HttpStatus.CREATED) ;
	}
	
	@GetMapping("/theatres/address")
	public ResponseEntity<List<TheatreDTO>> getTheatresByAddress(@RequestParam("query") String query) throws TheatreException {
		
		List<TheatreDTO> theatresInfoList = theatreService.findTheaterByCityORPincode(query);

		return new ResponseEntity<List<TheatreDTO>>(theatresInfoList,HttpStatus.OK) ;
	}
	
	@GetMapping("/theatres/name")
	public ResponseEntity<List<TheatreDTO>> getTheatresByName(@RequestParam("theatreName") String theatreName) throws TheatreException {
		
		List<TheatreDTO> theatresInfoList = theatreService.findTheatreByName(theatreName);

		return new ResponseEntity<List<TheatreDTO>>(theatresInfoList,HttpStatus.OK) ;
	}
	
	
}
