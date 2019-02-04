package com.infosupport.poc.ddd.domain.service;

import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;

public interface PaymentInstructionRepository {

	void add(PaymentInstruction paymentInstruction);
	
	PaymentInstruction find(Long paymentInstructionId) throws BusinessRuleNotSatisfied;
}
