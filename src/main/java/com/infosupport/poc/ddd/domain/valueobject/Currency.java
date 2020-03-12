package com.infosupport.poc.ddd.domain.valueobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Currency {

	private static final String USD = "USD";
	
	private String currencyCode;

    private Currency(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public static Optional<Currency> create(final String currencyCode, final List<String> validationMessages) {
        if (currencyCode == null || currencyCode.length() != 3) {
            validationMessages.add("Currency code has 3 characters");
            return Optional.empty();
        }
        return Optional.of(new Currency(currencyCode));
    }

    public static Optional<Currency> create(final String currencyCode) {
        return create(currencyCode, new ArrayList<>());
    }

    public String getCurrencyCode() {
		return currencyCode;
	}

    public boolean isUSD() { return USD.equals(currencyCode); }
}