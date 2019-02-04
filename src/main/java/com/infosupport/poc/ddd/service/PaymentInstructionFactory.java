package com.infosupport.poc.ddd.service;

import com.infosupport.poc.ddd.domain.entity.orderingaccount.OrderingAccount;
import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import com.infosupport.poc.ddd.domain.service.ForwardDateService;
import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;
import com.infosupport.poc.ddd.service.dto.PaymentInstructionDTO;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("ServicePaymentInstructionFactory")
public class PaymentInstructionFactory {

    @Resource
    private ForwardDateService forwardDateService;

    public PaymentInstruction createPaymentInstructionDO(final PaymentInstructionDTO dto) throws BusinessRuleNotSatisfied {

    	final List<String> validationMessages = new ArrayList<>();

		final Currency paymentCurrency = Currency.create(dto.getPaymentCurrency(), validationMessages);
		final Country beneficiaryBankCountry = Country.create(dto.getBeneficiaryBankCountry());

		return new PaymentInstruction(
				dto.getPaymentInstructionID(),
				OrderingAccount.create(dto.getOrderingAccountIdentification(), validationMessages),
				dto.getBeneficiaryAccountIdentification(),
				dto.getBeneficiaryAccountName(),
				dto.getBeneficiaryBankName(),
				dto.getBeneficiaryBankAddress(),
				beneficiaryBankCountry,
				paymentCurrency,
				dto.getFedwireCode(),
				dto.getAmount(),
				forwardDateService.calculateForwardDate(paymentCurrency),
				new LogTracerImpl(PaymentInstruction.class),
				validationMessages);
	}

	public PaymentInstructionDTO createPaymentInstructionDTO(final PaymentInstruction paymentInstruction) {
		return DozerBeanMapperSingletonWrapper.getInstance().map(paymentInstruction, PaymentInstructionDTO.class);
	}
}