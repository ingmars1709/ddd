package com.infosupport.poc.ddd.domain.rule;

import java.util.Arrays;
import java.util.List;

public final class BusinessRuleNotSatisfied extends Exception {

    private final List<String> validationMessages;

    public BusinessRuleNotSatisfied(final List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }

    public BusinessRuleNotSatisfied(String validationMessage) {
        this(Arrays.asList(validationMessage));
    }

    public List<String> getValidationMessages() {
        return validationMessages;
    }
}
