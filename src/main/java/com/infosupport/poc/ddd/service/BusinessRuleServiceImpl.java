package com.infosupport.poc.ddd.service;

import com.infosupport.poc.ddd.domain.rule.SemanticBusinessRuleMap;
import org.springframework.stereotype.Service;

@Service
public class BusinessRuleServiceImpl implements BusinessRuleService {
	
	@Override
	public boolean execute(BusinessRuleInvocation invocation) {
		return SemanticBusinessRuleMap.getRuleMap().get(invocation.getRule()).isApplicable(invocation.getParams());
	}
}