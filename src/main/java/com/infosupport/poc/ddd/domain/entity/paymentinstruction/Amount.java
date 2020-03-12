package com.infosupport.poc.ddd.domain.entity.paymentinstruction;

import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;

import java.math.BigDecimal;
import java.util.List;

public final class Amount {
    private BigDecimal value;

    private Amount(final String value) throws BusinessRuleNotSatisfied {
        try {
            this.value = new BigDecimal(value);

            if (this.value.signum() < 1) {
                invalidAmount();
            }
        } catch (NumberFormatException nfe) {
            invalidAmount();
        }
    }

    public static Amount create(final String amount, final List<String> validationMessages) {
        try {
            return new Amount(amount);
        } catch (final BusinessRuleNotSatisfied businessRuleNotSatisfied) {
            validationMessages.addAll(businessRuleNotSatisfied.getValidationMessages());
        }
        return null;
    }

    public BigDecimal getValue() {
        return value;
    }

    private void invalidAmount() throws BusinessRuleNotSatisfied {
        throw new BusinessRuleNotSatisfied("Invalid amount");
    }
}
