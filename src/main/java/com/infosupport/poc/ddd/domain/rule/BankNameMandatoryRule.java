package com.infosupport.poc.ddd.domain.rule;

import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;

public class BankNameMandatoryRule implements SemanticBusinessRule {

    @Override
    public void satisfiedBy(final PaymentInstruction paymentInstruction) throws BusinessRuleNotSatisfied {
        if (paymentInstruction != null
                && paymentInstruction.hasBankCountry()
                && !paymentInstruction.hasBankName()) {
            throw new BusinessRuleNotSatisfied("Bank name mandatory when Bank country set");
        }
    }
}
