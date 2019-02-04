package com.infosupport.poc.ddd.dao;

import com.infosupport.poc.ddd.dao.entity.OrderingAccount;
import com.infosupport.poc.ddd.dao.entity.PaymentInstructionEntity;
import com.infosupport.poc.ddd.domain.entity.paymentinstruction.PaymentInstruction;
import com.infosupport.poc.ddd.domain.rule.BusinessRuleNotSatisfied;
import com.infosupport.poc.ddd.domain.service.PaymentInstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PaymentInstructionRepositoryImpl implements PaymentInstructionRepository {

	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	private PaymentInstructionFactory paymentInstructionFactory;

	@Override	
	@Transactional(propagation=Propagation.REQUIRES_NEW)	
	public void add(PaymentInstruction paymentInstruction) {
		OrderingAccount orderingAccount = findOrderingAccount(paymentInstruction.getOrderingAccount().getOrderingAccountIdentification());
		
		PaymentInstructionEntity paymentInstructionEntity =
				paymentInstructionFactory.createPaymentInstructionEntity(paymentInstruction, orderingAccount);
		
		if (paymentInstructionEntity.isEdited()) {
			entityManager.merge(paymentInstructionEntity);
		} else {		
			entityManager.persist(paymentInstructionEntity);
		}			
		entityManager.flush();
	}

	@Override
	public PaymentInstruction find(Long paymentInstructionId) throws BusinessRuleNotSatisfied {
		PaymentInstructionEntity paymentInstructionEntity =
				entityManager.find(PaymentInstructionEntity.class, paymentInstructionId);
		
		return paymentInstructionFactory.createPaymentInstruction(paymentInstructionEntity);
	}
	
	private OrderingAccount findOrderingAccount(String orderingAccountIdentification) {
		return entityManager
				.createQuery("SELECT o FROM OrderingAccount o WHERE orderingAccountIdentification = :orderingAccountIdentification", OrderingAccount.class)
				.setParameter("orderingAccountIdentification", orderingAccountIdentification)
				.getSingleResult();	
	}
}