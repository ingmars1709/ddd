package com.infosupport.poc.ddd.domain.entity.paymentinstruction;


import com.infosupport.poc.ddd.domain.valueobject.Country;

public class ManualBeneficiaryBank {

	private String beneficiaryBankName;

    private String beneficiaryBankAddress;
    
    private Country beneficiaryBankCountry;
    
    private ManualBeneficiaryBank(final String beneficiaryBankName, final String beneficiaryBankAddress, final Country beneficiaryBankCountry) {
    	this.beneficiaryBankName = beneficiaryBankName;
    	this.beneficiaryBankAddress = beneficiaryBankAddress;
    	this.beneficiaryBankCountry = beneficiaryBankCountry;
    }

    public static ManualBeneficiaryBank create(final String beneficiaryBankName,
											   final String beneficiaryBankAddress,
											   final Country beneficiaryBankCountry) {
    	return new ManualBeneficiaryBank(beneficiaryBankName, beneficiaryBankAddress, beneficiaryBankCountry);
    }

    public String getBeneficiaryBankName() {
		return beneficiaryBankName;
	}

	public String getBeneficiaryBankAddress() {
		return beneficiaryBankAddress;
	}

	public Country getBeneficiaryBankCountry() {
		return beneficiaryBankCountry;
	}
}
