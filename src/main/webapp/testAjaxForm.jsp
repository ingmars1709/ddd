<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<form method="post" id="formAddPayment">
    <table>
    	<tr>
    		<td>Ordering Account</td>
    		<td><select name="orderingAccountIdentification" data-bind="options: orderingAccounts,
    																	optionsCaption: 'Select account',    																	
    																	value: orderingAccountIdentification"></td>
    	</tr>
    	<tr>
    	    <td>Currency</td>
    		<td><select name="paymentCurrency" data-bind="options: paymentCurrencies, 
    													  optionsCaption: 'Select currency',
    													  value: selectedPaymentCurrency"></select></td>
    	</tr>
    	<tr>
    	    <td>Amount</td>
    		<td><input type ="text" name="amount" data-bind="value: amount"></td>
    	</tr>
    	<tr>
    	   <td><br/></td>
    	</tr>
    	<tr>
    		<td>Beneficiary Account</td>
    		<td><input type="text" name="beneficiaryAccountIdentification" data-bind="value: beneficiaryAccountIdentification"></td>
    	</tr>
    	<tr>
    		<td>Beneficiary Account name</td>
    		<td><input type="text" name="beneficiaryAccountName" data-bind="value: beneficiaryAccountName"></td>
    	</tr>
    	<tr>
    	    <td><br/></td>
    	</tr>
	    <tr>
	        <td>Beneficiary Bank name</td>
	        <td><input type="text" name="beneficiaryBankName" data-bind="value: beneficiaryBankName"></td>
	    </tr>    
	    <tr>
	        <td>Beneficiary Bank address</td>
	        <td><input type="text" name="beneficiaryBankAddress" data-bind="value: beneficiaryBankAddress"></td>
	    </tr>
	    <tr>
	    	<td>Beneficiary Bank country</td>
	    	<td><select name="beneficiaryBankCountry" data-bind="options: beneficiaryBankCountries, 
	    														 optionsCaption: 'Select country',
	    														 value: selectedBeneficiaryBankCountry"></select></td>
	    </tr>
	    <tr data-bind="visible: fedwireFieldVisible">
	        <td>Fedwire</td>
	        <td><input type="text" name="fedwireCode" data-bind="value: fedwireCode"></td>
	    </tr>    
	</table>
	<input type="hidden" name="paymentInstructionID" data-bind="value: paymentInstructionID">
	<input name="submitPayment" type="submit" value="Add payment" data-bind="click: addPayment" />
	<input name="submitEditPayment" type="submit" value="Edit payment" data-bind="click: editPayment" />			  
</form>
 
<div class="jsonResponse"></div>

<script type='text/javascript' src="<spring:url value="/js/knockout-3.3.0.js" />"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery-1.10.2.min.js" />" charset="utf-8"></script>
<script type="text/javascript">
		var paymentViewModel = function paymentViewModel() {
				var _this = this;
				_this.paymentInstructionID = ko.observable();
				_this.orderingAccountIdentification = ko.observable();								
				_this.paymentCurrencies = ko.observableArray(['EUR','USD']);				
				_this.orderingAccounts = ko.observableArray(['NL91ABNA0441710441','NL91ABNA0524307784']);
				_this.beneficiaryBankCountries = ko.observableArray(['France','The Netherlands','United States of America']);
				
				_this.selectedPaymentCurrency = ko.observable();				
				_this.amount = ko.observable();
				_this.beneficiaryAccountIdentification = ko.observable();
				_this.beneficiaryAccountName = ko.observable();
				_this.selectedBeneficiaryBankCountry = ko.observable();				
				_this.beneficiaryBankName = ko.observable();				
				_this.beneficiaryBankAddress = ko.observable();				
				_this.fedwireCode = ko.observable();				
				
				_this.fedwireFieldVisible = ko.observable();
				
				_this.fedwireFieldVisible.subscribe(function(value) {
					if (!value) {
						_this.fedwireCode(null);
					}
				});
				
				_this.isVisible = function calculateIsVisible(rule, params, callback) {					
					$.ajax({
		                async: true,                
		                type: 'GET',
		                data: {rule : rule, params: params.toString()},
		                url: '${pageContext.request.contextPath}/isVisible',
		                dataType: 'json',                
		                cache: false,
		                success: function (data, textStatus, jqXHR) {                	
		                	callback(data);
		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                    alert(errorThrown);
		                }
		            });					
				};
				
				_this.computeFedwireFieldVisibility = ko.computed(function() {					
					_this.isVisible(
							"FEDWIRE_MANDATORY_RULE", 
							[_this.selectedPaymentCurrency(), _this.selectedBeneficiaryBankCountry()], 
							_this.fedwireFieldVisible);
				}).extend({rateLimit: 0});								
				
				_this.editPayment = function doEditPayment() {
					$.ajax({
		                async: true,                
		                type: 'GET',		                
		                url: '${pageContext.request.contextPath}/editAjax',
		                dataType: 'json',                
		                cache: false,
		                success: function (data, textStatus, jqXHR) {
		                	_this.paymentInstructionID(data.paymentInstructionID);
		                	_this.orderingAccountIdentification(data.orderingAccountIdentification);
							_this.selectedPaymentCurrency(data.paymentCurrency);
							_this.amount(data.amount);
							_this.beneficiaryAccountIdentification(data.beneficiaryAccountIdentification);
							_this.beneficiaryAccountName(data.beneficiaryAccountName);
							_this.beneficiaryBankName(data.beneficiaryBankName);
							_this.beneficiaryBankAddress(data.beneficiaryBankAddress)
							_this.selectedBeneficiaryBankCountry(data.beneficiaryBankCountry);
							_this.fedwireCode(data.fedwireCode);
		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                    alert(errorThrown);
		                }
		            });
				};
				
				_this.addPayment = function doSubmit() {								
						$.ajax({
		                async: true,                
		                type: 'POST',
		                data: $("form").serialize(),
		                url: '${pageContext.request.contextPath}/addAjax',
		                dataType: 'json',                
		                cache: false,
		                success: function (data, textStatus, jqXHR) {                	
		                	var element = $(".jsonResponse");              
		                	element.empty();

		                	for (i = 0; i < data.length; i++) {
                              element.append("<p>" + data[i] + "</p>");
                            }

		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                    alert(errorThrown);
		                }
		            });
				}
				
			};
		ko.applyBindings(paymentViewModel, document.getElementById('formAddPayment'));
</script>
</body>
</html>