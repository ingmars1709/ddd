package com.infosupport.poc.ddd.service;

import com.infosupport.poc.ddd.domain.entity.orderingaccount.OrderingAccount;
import com.infosupport.poc.ddd.domain.entity.paymentinstruction.*;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import com.infosupport.poc.ddd.domain.service.ForwardDateService;
import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;
import com.infosupport.poc.ddd.service.dto.PaymentInstructionDTO;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("ServicePaymentInstructionFactory")
public class PaymentInstructionFactory {

    @Resource
    private ForwardDateService forwardDateService;

    public PaymentInstruction createPaymentInstructionDO(final PaymentInstructionDTO dto) throws BusinessRuleNotSatisfied {

    	final List<String> msgs = new ArrayList<>();

		final Optional<Currency> paymentCurrency = Currency.create(dto.getPaymentCurrency(), msgs);
		final Country beneficiaryBankCountry = Country.create(dto.getBeneficiaryBankCountry());

		return new PaymentInstruction(
				dto.getPaymentInstructionID(),
				OrderingAccount.create(dto.getOrderingAccountIdentification(), msgs),
				BeneficiaryAccount.create(dto.getBeneficiaryAccountIdentification(), dto.getBeneficiaryAccountName(), msgs),
				ManualBeneficiaryBank.create(dto.getBeneficiaryBankName(), dto.getBeneficiaryBankAddress(), beneficiaryBankCountry),
				paymentCurrency.orElse(null),
				Fedwire.create(dto.getFedwireCode(), paymentCurrency, beneficiaryBankCountry, msgs).orElse(null),
				Amount.create(dto.getAmount(), msgs),
				calculateForwardDate(paymentCurrency),
				new LogTracerImpl(PaymentInstruction.class),
				msgs);
	}

	private LocalDateTime calculateForwardDate(final Optional<Currency> paymentCurrency) {
		return forwardDateService.calculateForwardDate(paymentCurrency);
	}

	public PaymentInstructionDTO createPaymentInstructionDTO(final PaymentInstruction paymentInstruction) {
		return DozerBeanMapperSingletonWrapper.getInstance().map(paymentInstruction, PaymentInstructionDTO.class);
	}
}