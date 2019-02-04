package com.infosupport.poc.ddd.domain.service;

import com.infosupport.poc.ddd.domain.valueobject.Currency;
import org.joda.time.LocalDateTime;

public class ForwardDateServiceImpl implements ForwardDateService {

	public ForwardDateServiceImpl() {
	}

	@Override
	public LocalDateTime calculateForwardDate(final Currency currency) {
		return new LocalDateTime().plusDays(2); // for now just a constant
	}
}