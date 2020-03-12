package com.infosupport.poc.ddd.domain.entity.paymentinstruction;

import com.infosupport.poc.ddd.domain.entity.Entity;
import com.infosupport.poc.ddd.domain.entity.orderingaccount.OrderingAccount;
import com.infosupport.poc.ddd.domain.entity.Tracer;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import com.infosupport.poc.ddd.domain.rule.SemanticBusinessRuleMap;
import com.infosupport.poc.ddd.domain.valueobject.Country;
import com.infosupport.poc.ddd.domain.valueobject.Currency;
import com.infosupport.poc.ddd.service.LogTracerImpl;
import org.joda.time.LocalDateTime;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isBlank;

public final class PaymentInstruction implements Entity<PaymentInstruction> {

	private Long paymentInstructionID;
	private OrderingAccount orderingAccount;
	private Currency paymentCurrency;
	private BeneficiaryAccount beneficiaryAccount;
    private ManualBeneficiaryBank beneficiaryBank;
    private Fedwire fedwire;
    private Amount amount;
	private LocalDateTime forwardDateTime;
	private Tracer tracer;

	public PaymentInstruction(final Long paymentInstructionID,
							  final OrderingAccount orderingAccount,
							  final BeneficiaryAccount beneficiaryAccount,
							  final ManualBeneficiaryBank beneficiaryBank,
							  final Currency paymentCurrency,
							  final Fedwire fedwire,
							  final Amount amount,
							  final LocalDateTime forwardDateTime,
							  final LogTracerImpl tracer,
							  final List<String> validationMessages) throws BusinessRuleNotSatisfied {
		this.paymentInstructionID = paymentInstructionID;
		this.paymentCurrency = paymentCurrency;
		this.orderingAccount = orderingAccount;
		this.beneficiaryAccount = beneficiaryAccount;
		this.beneficiaryBank = beneficiaryBank;
		this.fedwire = fedwire;
		this.amount = amount;
		this.forwardDateTime = forwardDateTime;
		this.tracer = tracer;

		SemanticBusinessRuleMap.satisfiedBy(this, validationMessages);

		tracer.trace("PI validated");
	}

	@Override
    public boolean hasSameIdentityAs(PaymentInstruction paymentInstruction) {
		return this.getPaymentInstructionID() != null && this.getPaymentInstructionID().equals(paymentInstruction.getPaymentInstructionID());
    }

	public boolean hasCurrencyUSD() {
		return getPaymentCurrency() != null && getPaymentCurrency().isUSD();
	}

	public boolean hasBankCountryUSA() {
		if (hasBeneficiaryBank()) {
			Country beneficiaryBankCountry = getBeneficiaryBank().getBeneficiaryBankCountry();

			if (beneficiaryBankCountry != null) {
				return beneficiaryBankCountry.isUSA();
			}
		}
		return false;
	}

	public boolean hasBankCountry() {
		return hasBeneficiaryBank()
				&& getBeneficiaryBank().getBeneficiaryBankCountry() != null
				&& !isBlank(getBeneficiaryBank().getBeneficiaryBankCountry().getCountryName());
	}

	public boolean hasBankName() {
		return hasBeneficiaryBank() && !isBlank(getBeneficiaryBank().getBeneficiaryBankName());
	}

	public boolean hasFedwire() { return fedwire != null; }

	private boolean hasBeneficiaryBank() {
		return getBeneficiaryBank() != null;
	}

	public Long getPaymentInstructionID() {
		return paymentInstructionID;
	}

	public OrderingAccount getOrderingAccount() {
		return orderingAccount;
	}

	public Currency getPaymentCurrency() {
		return paymentCurrency;
	}

	public Amount getAmount() {
		return amount;
	}

	public Fedwire getFedwire() {
		return fedwire;
	}

	public ManualBeneficiaryBank getBeneficiaryBank() { return beneficiaryBank;	}

	public BeneficiaryAccount getBeneficiaryAccount() { return beneficiaryAccount; }

	public LocalDateTime getForwardDateTime() {
		return forwardDateTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PaymentInstruction that = (PaymentInstruction) o;

		if (getPaymentInstructionID() != null ? !getPaymentInstructionID().equals(that.getPaymentInstructionID()) : that.getPaymentInstructionID() != null)
			return false;
		if (!getOrderingAccount().equals(that.getOrderingAccount())) return false;
		if (!getPaymentCurrency().equals(that.getPaymentCurrency())) return false;
		if (!getBeneficiaryAccount().equals(that.getBeneficiaryAccount())) return false;
		if (!getBeneficiaryBank().equals(that.getBeneficiaryBank())) return false;
		if (getFedwire() != null ? !getFedwire().equals(that.getFedwire()) : that.getFedwire() != null)
			return false;
		if (!getAmount().equals(that.getAmount())) return false;
		return getForwardDateTime().equals(that.getForwardDateTime());
	}

	@Override
	public int hashCode() {
		int result = getPaymentInstructionID() != null ? getPaymentInstructionID().hashCode() : 0;
		result = 31 * result + getOrderingAccount().hashCode();
		result = 31 * result + getPaymentCurrency().hashCode();
		result = 31 * result + getBeneficiaryAccount().hashCode();
		result = 31 * result + getBeneficiaryBank().hashCode();
		result = 31 * result + (getFedwire() != null ? getFedwire().hashCode() : 0);
		result = 31 * result + getAmount().hashCode();
		result = 31 * result + getForwardDateTime().hashCode();
		return result;
	}
}