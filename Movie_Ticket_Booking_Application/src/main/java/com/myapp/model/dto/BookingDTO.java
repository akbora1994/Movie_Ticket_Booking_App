package com.myapp.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.myapp.enums.BookingStatus;
import com.myapp.model.Shows;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {


	private Integer bookingId;
	
	private Integer userId;

	private Shows shows;
	
	private List<Integer> seatsId = new ArrayList<>();
	
	private BookingStatus bookingStatus; 
	private Double totalBillAmount;
	private LocalDateTime bookingDateTime;
}








