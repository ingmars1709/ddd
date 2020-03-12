package com.infosupport.poc.ddd.domain.entity.paymentinstruction;

import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.infosupport.poc.ddd.domain.rule.SemanticBusinessRuleMap.getFedwireMandatoryRule;

public final class Fedwire {

    private final String value;

    private static final Pattern fedwirePattern = Pattern.compile("^[0-9]{9}$");

    private Fedwire(final String code)  {
        this.value = code;
    }

    public static Optional<Fedwire> create(final String fedwireCode,
                                          final Optional<Currency> paymentCurrency,
                                          final Country beneficiaryBankCountry,
                                          final List<String> validationMessages) {
        if (getFedwireMandatoryRule().isApplicable(paymentCurrency, beneficiaryBankCountry)
                && !fedwirePattern.matcher(fedwireCode).matches()) {
            validationMessages.add("Fedwire has 9 digits");
            return Optional.empty();
        }
        return Optional.of(new Fedwire(fedwireCode));
    }

    public String getValue() {
        return value;
    }
}