package com.infosupport.poc.ddd.domain.service;

import com.infosupport.poc.ddd.domain.valueobject.Currency;
import org.joda.time.LocalDateTime;

import java.util.Optional;

public class ForwardDateServiceImpl implements ForwardDateService {

	public ForwardDateServiceImpl() {
	}

	@Override
	public LocalDateTime calculateForwardDate(final Optional<Currency> currency) {
		return new LocalDateTime().plusDays(2); // for now just a constant
	}
}