package com.infosupport.poc.ddd.domain.rule;

import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;
import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;

import java.util.Arrays;
import java.util.List;

public final class FedwireMandatoryRule implements SemanticBusinessRule {

	public FedwireMandatoryRule() {	}

	@Override
	public boolean isApplicable(final List<String> params) {
		return 	params != null && params.size() == 2 &&	isApplicable(Currency.create(params.get(0)), Country.create(params.get(1)));
    }

	public boolean isApplicable(final Currency paymentCurrency, final Country beneficiaryBankCountry) {
		return paymentCurrency != null && beneficiaryBankCountry != null && paymentCurrency.isUSD() && beneficiaryBankCountry.isUSA();
	}

	@Override
	public void satisfiedBy(final PaymentInstruction paymentInstruction) throws BusinessRuleNotSatisfied {
		if (paymentInstruction != null
				&& paymentInstruction.hasCurrencyUSD()
				&& paymentInstruction.hasBankCountryUSA()
				&& !paymentInstruction.hasFedwire()) {
			throw new BusinessRuleNotSatisfied(Arrays.asList("Fedwire mandatory with USD currency and beneficiary bank in USA"));
		}
	}
}