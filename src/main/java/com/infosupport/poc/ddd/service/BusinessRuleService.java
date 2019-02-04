package com.infosupport.poc.ddd.service;

public interface BusinessRuleService {
	boolean execute(BusinessRuleInvocation invocation);
}
