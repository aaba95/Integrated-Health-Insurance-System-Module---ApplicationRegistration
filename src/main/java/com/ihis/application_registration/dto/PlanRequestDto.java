package com.ihis.application_registration.dto;


import javax.validation.constraints.NotBlank;


public class PlanRequestDto {
	
	
	
	@NotBlank(message = "plane name should not be blank")
	private String planName;
	
	@NotBlank(message = "application registration number should not be blank")
	private String applicationRegisterNo;

	
	

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getApplicationRegisterNo() {
		return applicationRegisterNo;
	}

	public void setApplicationRegisterNo(String applicationRegisterNo) {
		this.applicationRegisterNo = applicationRegisterNo;
	}
	
	
	
}
