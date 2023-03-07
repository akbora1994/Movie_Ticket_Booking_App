package com.myapp.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.myapp.enums.BookingStatus;
import com.myapp.model.Address;
import com.myapp.model.Seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingInfoDTO {

	private Integer bookingId;
	
	private Integer userId;

	private Integer showsId;
	private Integer screenId;
	private String screenName;
	private Integer theatreId;
	private String theatreName;
	private Address theatreAddress;
	private Integer moveiId;
	private String moveiName;
	private String moviePoster;
	
	private List<Seat> seats = new ArrayList<>();
	
	private BookingStatus bookingStatus; 
	private Double totalBillAmount;
	private LocalDateTime showDateTiming;
	
}
