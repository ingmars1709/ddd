package com.infosupport.poc.ddd.domain.rule;

import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;

public class MandatoryRule implements SemanticBusinessRule {

    @Override
    public void satisfiedBy(PaymentInstruction paymentInstruction) throws BusinessRuleNotSatisfied {
        if (paymentInstruction != null &&
                (paymentInstruction.getBeneficiaryAccount() == null
                || paymentInstruction.getPaymentCurrency() == null
                || paymentInstruction.getOrderingAccount() == null
                || paymentInstruction.getAmount() == null)) {
            throw new BusinessRuleNotSatisfied("Payment is missing mandatory fields");
        }
    }
}
