package com.infosupport.poc.ddd.service;

import java.util.List;

import com.infosupport.poc.ddd.domain.rule.SemanticBusinessRuleKey;

public class BusinessRuleInvocation {
	
	private SemanticBusinessRuleKey rule;
			
	private List<String> params;
	
	public BusinessRuleInvocation() {
		super();
	}
	
	public SemanticBusinessRuleKey getRule() {
		return rule;
	}

	public void setRule(SemanticBusinessRuleKey rule) {
		this.rule = rule;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}
}