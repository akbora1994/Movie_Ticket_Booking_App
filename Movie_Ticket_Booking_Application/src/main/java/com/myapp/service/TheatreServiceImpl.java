package com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.exception.TheatreException;
import com.myapp.model.Address;
import com.myapp.model.Theatre;
import com.myapp.model.dto.TheatreDTO;
import com.myapp.repository.TheatreRepo;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepo theatreRepo;
	
	@Override
	public Theatre registerTheater(Theatre theatre) throws TheatreException {
		
		return theatreRepo.save(theatre) ;
		
	}

	@Override
	public List<TheatreDTO> findTheaterByCityORPincode(String query) throws TheatreException {
		
		List<Theatre> theatres = theatreRepo.findTheatreByAddress(query);
		
		if(theatres.size() == 0) {
			throw new TheatreException("Theatres not found by Query : "+query) ;
		}
		
		List<TheatreDTO> theatreInfoList = this.copyPropertiesInDTO(theatres);
		
		return theatreInfoList;
	}

	@Override
	public List<TheatreDTO> findTheatreByName(String theatreName) throws TheatreException {
		
		List<Theatre> theatres = theatreRepo.findByName(theatreName);
		
		if(theatres.size() == 0) 
			throw new TheatreException("Theatres not found with Name : "+theatreName) ;
		
		return this.copyPropertiesInDTO(theatres);
	}
	
	public List<TheatreDTO> copyPropertiesInDTO(List<Theatre> theatres) {
		
		List<TheatreDTO> theatreInfoList = new ArrayList<>();
		
		for(Theatre theatre : theatres) {
			TheatreDTO theatreDTO = new TheatreDTO();
			BeanUtils.copyProperties(theatre, theatreDTO);
			theatreInfoList.add(theatreDTO);
		}
		
		return theatreInfoList;
	}
	

}
