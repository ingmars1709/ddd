package com.infosupport.poc.ddd.domain.rule;

import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class SemanticBusinessRuleMap {

	private static final EnumMap<SemanticBusinessRuleKey, SemanticBusinessRule> ruleMap = new EnumMap<>(SemanticBusinessRuleKey.class);

	private static final FedwireMandatoryRule fedwireMandatoryRule = new FedwireMandatoryRule();

	static {
		ruleMap.put(SemanticBusinessRuleKey.FEDWIRE_MANDATORY_RULE, fedwireMandatoryRule);
		ruleMap.put(SemanticBusinessRuleKey.BANK_NAME_MANDATORY_RULE, new BankNameMandatoryRule());
		ruleMap.put(SemanticBusinessRuleKey.MANDATORY_RULE, new MandatoryRule());
	}
		
	private SemanticBusinessRuleMap() {	}

	public static void satisfiedBy(final PaymentInstruction paymentInstruction, final List<String> msgs) throws BusinessRuleNotSatisfied {
		ruleMap.values().stream().forEach(rule -> {
			try {
				rule.satisfiedBy(paymentInstruction);
			} catch (final BusinessRuleNotSatisfied brnse) {
				msgs.addAll(brnse.getValidationMessages());
			}
		});

	    if (!msgs.isEmpty()) {
	    	throw new BusinessRuleNotSatisfied(msgs);
		}
    }

	public static Map<SemanticBusinessRuleKey, SemanticBusinessRule> getRuleMap() {
		return Collections.unmodifiableMap(ruleMap);
	}

	public static FedwireMandatoryRule getFedwireMandatoryRule() {
	    return fedwireMandatoryRule;
    }
}