package com.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.myapp.exception.PaymentException;

public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private BookingService bookingService;
	
	
	@Override
	public boolean paymentSucessfull() throws PaymentException {
		
		return true;
	}

	@Override
	public boolean paymentUncessfull(Integer bookingId, Integer userId) {
		
		return false;
	}

}
