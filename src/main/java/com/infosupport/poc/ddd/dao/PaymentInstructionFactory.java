package com.infosupport.poc.ddd.dao;

import com.infosupport.poc.ddd.dao.entity.OrderingAccount;
import com.infosupport.poc.ddd.dao.entity.PaymentInstructionEntity;
import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;
import com.infosupport.poc.ddd.service.LogTracerImpl;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DAOPaymentInstructionFactory")
public class PaymentInstructionFactory {

	public PaymentInstructionEntity createPaymentInstructionEntity(final PaymentInstruction paymentInstruction, final OrderingAccount orderingAccount) {
		final PaymentInstructionEntity pi =
				DozerBeanMapperSingletonWrapper.getInstance().map(paymentInstruction, PaymentInstructionEntity.class);

		pi.setOrderingAccount(orderingAccount);
		return pi;
	}
	
	public PaymentInstruction createPaymentInstruction(final PaymentInstructionEntity paymentInstructionEntity) throws BusinessRuleNotSatisfied {
		final List<String> validationMessages = new ArrayList<>();

        final Currency paymentCurrency = Currency.create("USD", validationMessages);
        final Country beneficiaryBankCountry = Country.create("United States of America");

        return new PaymentInstruction(
                paymentInstructionEntity.getId(),
                com.infosupport.poc.ddd.domain.entity.orderingaccount.OrderingAccount.create(paymentInstructionEntity.getOrderingAccount().getOrderingAccountIdentification(), validationMessages),
                "DE89 3704 0044 0532 0130 00",
				"Piet",
                "foo",
				"bar",
				beneficiaryBankCountry,
                paymentCurrency,
                "123456789",
                "123",
                paymentInstructionEntity.getForwardDateTime(),
                new LogTracerImpl(PaymentInstruction.class),
                validationMessages
        );
	}
}