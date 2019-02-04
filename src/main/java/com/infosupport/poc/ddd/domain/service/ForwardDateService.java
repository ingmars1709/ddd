package com.infosupport.poc.ddd.domain.service;

import com.infosupport.poc.ddd.domain.valueobject.Currency;
import org.joda.time.LocalDateTime;

public interface ForwardDateService {

	LocalDateTime calculateForwardDate(final Currency currency);

}