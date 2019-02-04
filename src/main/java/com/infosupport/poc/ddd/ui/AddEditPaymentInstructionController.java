package com.infosupport.poc.ddd.ui;

import com.infosupport.poc.ddd.service.BusinessRuleInvocation;
import com.infosupport.poc.ddd.service.BusinessRuleService;
import com.infosupport.poc.ddd.service.PaymentInstructionService;
import com.infosupport.poc.ddd.service.dto.PaymentInstructionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddEditPaymentInstructionController {
    
    @Autowired
	private PaymentInstructionService paymentInstructionService;
    
    @Autowired
	private BusinessRuleService businessRuleService;

    public AddEditPaymentInstructionController() {
    	super();
    }

    @RequestMapping(value = "/addAjax", method = RequestMethod.GET)
    public String getFormAjax(ModelMap model) {    	
    	return "testAjaxForm";
    }
    
    @RequestMapping(value = "/addAjax", method = RequestMethod.POST)
    @ResponseBody
    public List<String> postFormAjax(final PaymentInstructionDTO paymentInstructionDTO, final HttpServletRequest request) {
    	return paymentInstructionService.addPayment(paymentInstructionDTO);
    }
    
    @RequestMapping(value = "/editAjax", method = RequestMethod.GET)
    @ResponseBody
    public PaymentInstructionDTO getFormAjaxEdit() {
    	return paymentInstructionService.editPayment(1L);
    }
    
    @RequestMapping(value ="/isVisible", method = RequestMethod.GET)
    @ResponseBody
    public boolean isVisible(BusinessRuleInvocation invocation) {    	
    	return businessRuleService.execute(invocation);
    }        
}