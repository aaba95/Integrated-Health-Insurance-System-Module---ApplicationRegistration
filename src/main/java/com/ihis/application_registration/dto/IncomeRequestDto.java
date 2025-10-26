package com.ihis.application_registration.dto;


import javax.validation.constraints.NotNull;

public class IncomeRequestDto {

    @NotNull
	private Double monthlyIncome;
	
	
	private Double rentIncome;
	
	
	private Double propertyIncome;
	
	private String applicationRegisterNo;
	
	


	public String getApplicationRegisterNo() {
		return applicationRegisterNo;
	}


	public void setApplicationRegisterNo(String applicationRegisterNo) {
		this.applicationRegisterNo = applicationRegisterNo;
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
