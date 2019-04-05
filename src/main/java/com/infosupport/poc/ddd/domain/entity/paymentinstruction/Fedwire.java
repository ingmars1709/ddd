package com.infosupport.poc.ddd.domain.entity.paymentinstruction;

import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static com.infosupport.poc.ddd.domain.rule.SemanticBusinessRuleMap.getFedwireMandatoryRule;

public final class Fedwire {

    private final String value;

    private static final Pattern fedwirePattern = Pattern.compile("^[0-9]{9}$");

    private Fedwire(final String code) throws BusinessRuleNotSatisfied {
        if (!fedwirePattern.matcher(code).matches()) {
            throw new BusinessRuleNotSatisfied(Arrays.asList("Fedwire has 9 digits"));
        }
        this.value = code;
    }

    static Fedwire create(final String fedwireCode,
                          final Currency paymentCurrency,
                          final Country beneficiaryBankCountry,
                          final List<String> validationMessages) {
        try {
            if (getFedwireMandatoryRule().isApplicable(paymentCurrency, beneficiaryBankCountry)) {
                return new Fedwire(fedwireCode);
            }
        } catch (final BusinessRuleNotSatisfied businessRuleNotSatisfied) {
            validationMessages.addAll(businessRuleNotSatisfied.getValidationMessages());
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}