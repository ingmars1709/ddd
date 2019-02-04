package com.infosupport.poc.ddd.dao.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class PaymentInstructionEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne	
	@JoinColumn(name = "orderingAccountId")
	private OrderingAccount orderingAccount;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime forwardDateTime;

	public PaymentInstructionEntity() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getForwardDateTime() {
		return forwardDateTime;
	}

	public void setForwardDateTime(LocalDateTime forwardDateTime) {
		this.forwardDateTime = forwardDateTime;
	}

	public OrderingAccount getOrderingAccount() {
		return orderingAccount;
	}

	public void setOrderingAccount(OrderingAccount orderingAccount) {
		this.orderingAccount = orderingAccount;
	}

	public boolean isEdited() {
		return getId() != null;
	}
}
