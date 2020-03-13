package com.infosupport.poc.ddd.dao;

import com.infosupport.poc.ddd.dao.entity.OrderingAccount;
import com.infosupport.poc.ddd.dao.entity.PaymentInstructionEntity;
import com.infosupport.poc.ddd.domain.entity.paymentinstruction.*;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;
import com.infosupport.poc.ddd.service.LogTracerImpl;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("DAOPaymentInstructionFactory")
public class PaymentInstructionFactory {

	public PaymentInstructionEntity createPaymentInstructionEntity(
			final PaymentInstruction paymentInstruction, final OrderingAccount orderingAccount) {

		final PaymentInstructionEntity pi =
				DozerBeanMapperSingletonWrapper.getInstance().map(paymentInstruction, PaymentInstructionEntity.class);

		pi.setOrderingAccount(orderingAccount);

		return pi;
	}
	
	public PaymentInstruction createPaymentInstruction(
			final PaymentInstructionEntity paymentInstructionEntity) throws BusinessRuleNotSatisfied {

		final List<String> msgs = new ArrayList<>();

        final Optional<Currency> paymentCurrency = Currency.create("USD", msgs);
        final Country beneficiaryBankCountry = Country.create("United States of America");

		return new PaymentInstruction(
                paymentInstructionEntity.getId(),
				com.infosupport.poc.ddd.domain.entity.orderingaccount.OrderingAccount
						.create(paymentInstructionEntity.getOrderingAccount().getOrderingAccountIdentification(), msgs),
				BeneficiaryAccount.create("DE89370400440532013000", "Piet", msgs),
				ManualBeneficiaryBank.create("The Bank", null, beneficiaryBankCountry),
                paymentCurrency.orElse(null),
                Fedwire.create("123456789", paymentCurrency, beneficiaryBankCountry, msgs).orElse(null),
				Amount.create("123", msgs),
                paymentInstructionEntity.getForwardDateTime(),
                new LogTracerImpl(PaymentInstruction.class),
                msgs
        );
	}
}