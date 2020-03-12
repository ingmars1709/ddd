package com.infosupport.poc.ddd.service;

import com.infosupport.poc.ddd.service.dto.PaymentInstructionDTO;

import java.util.List;

public interface PaymentInstructionService {

	List<String> addPayment(final PaymentInstructionDTO paymentInstructionDTO);
	
	PaymentInstructionDTO editPayment(final Long paymentInstructionID);
}