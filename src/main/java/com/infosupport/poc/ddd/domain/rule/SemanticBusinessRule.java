package com.infosupport.poc.ddd.domain.rule;

import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;

import java.util.List;

public interface SemanticBusinessRule {
	
	default boolean isApplicable(final List<String> params) { return true; }

	void satisfiedBy(final PaymentInstruction paymentInstruction) throws BusinessRuleNotSatisfied;
}
