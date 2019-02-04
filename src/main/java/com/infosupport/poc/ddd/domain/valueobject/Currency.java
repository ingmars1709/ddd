package com.infosupport.poc.ddd.domain.valueobject;

import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;

import java.util.List;

public final class Currency {

	private static final String USD = "USD";
	
	private String currencyCode;

    private Currency(final String currencyCode) throws BusinessRuleNotSatisfied {
    	if (currencyCode == null || currencyCode.length() != 3) {
            throw new BusinessRuleNotSatisfied("Currency code has 3 characters");
		}
        this.currencyCode = currencyCode;
    }

    public static Currency create(final String currency, final List<String> validationMessages) {
        try {
            return new Currency(currency);
        } catch (final BusinessRuleNotSatisfied businessRuleNotSatisfied) {
            validationMessages.addAll(businessRuleNotSatisfied.getValidationMessages());
        }
        return null;
    }

    public static Currency create(final String currency) {
        try {
            return new Currency(currency);
        } catch (final BusinessRuleNotSatisfied businessRuleNotSatisfied) {
            return null;
        }
    }

    public String getCurrencyCode() {
		return currencyCode;
	}

    public boolean isUSD() { return USD.equals(currencyCode); }
}