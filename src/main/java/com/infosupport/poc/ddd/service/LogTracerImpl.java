package com.infosupport.poc.ddd.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infosupport.poc.ddd.domain.entity.Tracer;

public class LogTracerImpl implements Tracer {

	private Logger logger;

	public <T> LogTracerImpl(Class<T> clazz) {
		logger = LogManager.getLogger(clazz);
	}
	
	@Override
	public void trace(String message) {
		logger.info(message);
	}
}
