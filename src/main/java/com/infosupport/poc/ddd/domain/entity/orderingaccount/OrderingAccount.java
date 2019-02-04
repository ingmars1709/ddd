package com.infosupport.poc.ddd.domain.entity.orderingaccount;

import com.infosupport.poc.ddd.domain.entity.Entity;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class OrderingAccount implements Entity<OrderingAccount> {

	private String orderingAccountIdentification;

	private OrderingAccount(final String orderingAccountIdentification) throws BusinessRuleNotSatisfied {
	    if (StringUtils.isBlank(orderingAccountIdentification)) {
	        throw new BusinessRuleNotSatisfied("Ordering account identification is mandatory");
        }
		this.orderingAccountIdentification = orderingAccountIdentification;
	}

	public static OrderingAccount create(final String orderingAccountIdentification, final List<String> validationMessages) {
		try {
			return new OrderingAccount(orderingAccountIdentification);
		} catch (final BusinessRuleNotSatisfied businessRuleNotSatisfied) {
			validationMessages.addAll(businessRuleNotSatisfied.getValidationMessages());
		}
		return null;
	}

	@Override
	public boolean hasSameIdentityAs(OrderingAccount entity) {	
		return this.orderingAccountIdentification.equals(entity.getOrderingAccountIdentification());
	}
	
	public String getOrderingAccountIdentification() {
		return orderingAccountIdentification;
	}
}
