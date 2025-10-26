package com.ihis.application_registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DC_INCOME_DTLS")
public class Income {

	@Id
	@Column(name = "INCOME_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="APPLICATION_REG_NO")
	private String applicationRegisterNo;
	
	@Column(name = "MONTHLY_INCOME")
	private Double monthlyIncome;
	
	@Column(name = "RENT_INCOME")
	private Double rentIncome;
	
	@Column(name = "PROPERTY_INCOME")
	private Double propertyIncome;

	
	public String getApplicationRegisterNo() {
		return applicationRegisterNo;
	}

	public void setApplicationRegisterNo(String applicationRegisterNo) {
		this.applicationRegisterNo = applicationRegisterNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Double getRentIncome() {
		return rentIncome;
	}

	public void setRentIncome(Double rentIncome) {
		this.rentIncome = rentIncome;
	}

	public Double getPropertyIncome() {
		return propertyIncome;
	}

	public void setPropertyIncome(Double propertyIncome) {
		this.propertyIncome = propertyIncome;
	}
	
	
	
	
	
}
