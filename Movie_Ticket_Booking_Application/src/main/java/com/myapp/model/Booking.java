package com.myapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.myapp.enums.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	private Integer userId;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Shows shows;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Seat> seats = new ArrayList<>();
	
	private BookingStatus bookingStatus;
	
	private Double totalBillAmount;
	
	private LocalDateTime bookingDateTime;
	
	

	public Booking(Integer userId, Shows shows, BookingStatus bookingStatus,Double totalBillAmount,LocalDateTime bookingDateTime) {
		super();
		this.userId = userId;
		this.shows = shows;
		this.bookingStatus = bookingStatus;
		this.totalBillAmount = totalBillAmount;
	    this.bookingDateTime = bookingDateTime;
	
	} 
	

}
