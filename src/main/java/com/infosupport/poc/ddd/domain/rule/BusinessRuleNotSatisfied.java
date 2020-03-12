package com.infosupport.poc.ddd.domain.rule;

import java.util.List;

import static java.util.Arrays.asList;

public final class BusinessRuleNotSatisfied extends Exception {

    private final List<String> validationMessages;

    public BusinessRuleNotSatisfied(final List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }

    public BusinessRuleNotSatisfied(final String validationMessage) {
        this(asList(validationMessage));
    }

    public List<String> getValidationMessages() {
        return validationMessages;
    }
}
