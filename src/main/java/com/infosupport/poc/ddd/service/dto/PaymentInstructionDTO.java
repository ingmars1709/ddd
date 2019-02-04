package com.infosupport.poc.ddd.service.dto;

public class PaymentInstructionDTO {

	private Long paymentInstructionID;
	
	private String orderingAccountIdentification;
	
	private String paymentCurrency;

	private String beneficiaryAccountIdentification;
		
	private String beneficiaryAccountName;
	
    private String beneficiaryBankName;

    private String beneficiaryBankAddress;
    
    private String beneficiaryBankCountry;
    
    private String fedwireCode;
    
    private String amount;

    public PaymentInstructionDTO() {    }
    
	public String getFedwireCode() {
		return fedwireCode;
	}

	public void setFedwireCode(String fedwireCode) {
		this.fedwireCode = fedwireCode;
	}

	public String getBeneficiaryBankAddress() {
		return beneficiaryBankAddress;
	}

	public void setBeneficiaryBankAddress(String beneficiaryBankAddress) {
		this.beneficiaryBankAddress = beneficiaryBankAddress;
	}

	public String getBeneficiaryBankName() {
		return beneficiaryBankName;
	}

	public void setBeneficiaryBankName(String beneficiaryBankName) {
		this.beneficiaryBankName = beneficiaryBankName;
	}

	public String getBeneficiaryBankCountry() {
		return beneficiaryBankCountry;
	}

	public void setBeneficiaryBankCountry(String beneficiaryBankCountry) {
		this.beneficiaryBankCountry = beneficiaryBankCountry;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getBeneficiaryAccountIdentification() {
		return beneficiaryAccountIdentification;
	}

	public void setBeneficiaryAccountIdentification(String beneficiaryAccountIdentification) {
		this.beneficiaryAccountIdentification = beneficiaryAccountIdentification;
	}

	public String getBeneficiaryAccountName() {
		return beneficiaryAccountName;
	}

	public void setBeneficiaryAccountName(String beneficiaryAccountName) {
		this.beneficiaryAccountName = beneficiaryAccountName;
	}

	public String getOrderingAccountIdentification() {
		return orderingAccountIdentification;
	}

	public void setOrderingAccountIdentification(String orderingAccountIdentification) {
		this.orderingAccountIdentification = orderingAccountIdentification;
	}

	public Long getPaymentInstructionID() {
		return paymentInstructionID;
	}

	public void setPaymentInstructionID(Long paymentInstructionID) {
		this.paymentInstructionID = paymentInstructionID;
	}
}
