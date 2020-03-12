package com.infosupport.poc.ddd.domain.service;

import com.infosupport.poc.ddd.domain.valueobject.Currency;
import org.joda.time.LocalDateTime;

import java.util.Optional;

public interface ForwardDateService {

	LocalDateTime calculateForwardDate(final Optional<Currency> currency);

}