package com.myapp.service;

import com.myapp.exception.PaymentException;

public interface PaymentService {

	public boolean paymentSucessfull() throws PaymentException;
	
	public boolean paymentUncessfull(Integer bookingId,Integer userId) ;
	
}
