package com.infosupport.poc.ddd.service;

import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import com.infosupport.poc.ddd.domain.service.PaymentInstructionRepository;
import com.infosupport.poc.ddd.service.dto.PaymentInstructionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PaymentInstructionServiceImpl implements PaymentInstructionService {

	@Autowired
	private PaymentInstructionRepository paymentInstructionRepository;

	@Autowired
	private PaymentInstructionFactory paymentInstructionFactory;
	
	@Override
	public List<String> addPayment(final PaymentInstructionDTO paymentInstructionDTO) {
		try {
			final PaymentInstruction paymentInstruction =
					paymentInstructionFactory.createPaymentInstructionDO(paymentInstructionDTO);

			paymentInstructionRepository.add(paymentInstruction);

		} catch (final BusinessRuleNotSatisfied businessRuleNotSatisfied) {
			return businessRuleNotSatisfied.getValidationMessages();
		}
		return Collections.singletonList("OK");
	}

	@Override
	public PaymentInstructionDTO editPayment(final Long paymentInstructionID) {
		PaymentInstruction paymentInstruction = null;
		try {
			paymentInstruction = paymentInstructionRepository.find(paymentInstructionID);
		} catch (BusinessRuleNotSatisfied businessRuleNotSatisfied) {
			businessRuleNotSatisfied.printStackTrace();
		}
		return paymentInstructionFactory.createPaymentInstructionDTO(paymentInstruction);
	}		
}