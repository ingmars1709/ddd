package com.infosupport.poc.ddd.domain.entity.paymentinstruction;

import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import org.apache.commons.lang.StringUtils;
import org.iban4j.*;

import java.util.List;

public final class BeneficiaryAccount {
	
	private String beneficiaryAccountIdentification;
	
	private String beneficiaryAccountName;
		
	private BeneficiaryAccount(final String beneficiaryAccountIdentification, final String beneficiaryAccountName)
			throws BusinessRuleNotSatisfied {

		if (StringUtils.isBlank(beneficiaryAccountIdentification)) {
			throw new BusinessRuleNotSatisfied("Beneficiary Account is mandatory");
		}

		try {
			IbanUtil.validate(beneficiaryAccountIdentification);
		} catch (IbanFormatException | InvalidCheckDigitException |	UnsupportedCountryException e) {
			throw new BusinessRuleNotSatisfied("Beneficiary Account is not a valid IBAN (e.g.: DE89370400440532013000)");
		}

		this.beneficiaryAccountIdentification = beneficiaryAccountIdentification;
		this.beneficiaryAccountName = beneficiaryAccountName;
	}

    public static BeneficiaryAccount create(final String beneficiaryAccountIdentification,
									        final String beneficiaryAccountName,
									        final List<String> validationMessages) {
		try {
			return new BeneficiaryAccount(beneficiaryAccountIdentification, beneficiaryAccountName);
		} catch (final BusinessRuleNotSatisfied businessRuleNotSatisfied) {
			validationMessages.addAll(businessRuleNotSatisfied.getValidationMessages());
		}
		return null;
    }

    public String getBeneficiaryAccountIdentification() {
		return beneficiaryAccountIdentification;
	}

	public String getBeneficiaryAccountName() {
		return beneficiaryAccountName;
	}
}