package com.infosupport.poc.ddd.domain.entity;

public interface Entity<T> {
	
	boolean hasSameIdentityAs(T entity);
}