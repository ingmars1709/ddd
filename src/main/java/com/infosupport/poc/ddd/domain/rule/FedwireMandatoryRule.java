package com.infosupport.poc.ddd.domain.rule;

import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;
import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;

import java.util.List;
import java.util.Optional;

public final class FedwireMandatoryRule implements SemanticBusinessRule {

	public FedwireMandatoryRule() {	}

	@Override
	public boolean isApplicable(final List<String> params) {
		return 	params != null && params.size() == 2 &&	isApplicable(Currency.create(params.get(0)), Country.create(params.get(1)));
    }

	public boolean isApplicable(final Optional<Currency> paymentCurrency, final Country beneficiaryBankCountry) {
		return paymentCurrency.isPresent() && beneficiaryBankCountry != null && paymentCurrency.get().isUSD() && beneficiaryBankCountry.isUSA();
	}

	@Override
	public void satisfiedBy(final PaymentInstruction paymentInstruction) throws BusinessRuleNotSatisfied {
		if (paymentInstruction != null
				&& paymentInstruction.hasCurrencyUSD()
				&& paymentInstruction.hasBankCountryUSA()
				&& !paymentInstruction.hasFedwire()) {
			throw new BusinessRuleNotSatisfied("Fedwire mandatory with USD currency and beneficiary bank in USA");
		}
	}
}