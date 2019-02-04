package com.infosupport.poc.ddd.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderingAccount {
	
	@Id	
	private Long id;
	
	private String orderingAccountIdentification;
	
	public OrderingAccount() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderingAccountIdentification() {
		return orderingAccountIdentification;
	}

	public void setOrderingAccountIdentification(String orderingAccountIdentification) {
		this.orderingAccountIdentification = orderingAccountIdentification;
	}
}