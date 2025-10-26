package com.ihis.application_registration.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class KidsRequestDto {
	
	@NotBlank(message = "Kid name should not be blank")
	private String kidName;
	
	@NotNull
	private Integer kidAge;
	
	@NotBlank(message = "ssn should not be blank")
	private String ssn;
	
	private Integer citizenID;
	
	@NotBlank(message = "APP_REG_NO should not be blank")
	private String applicationRegisterNo;
	
	

	public Integer getCitizenID() {
		return citizenID;
	}

	public void setCitizenID(Integer citizenID) {
		this.citizenID = citizenID;
	}

	public String getApplicationRegisterNo() {
		return applicationRegisterNo;
	}

	public void setApplicationRegisterNo(String applicationRegisterNo) {
		this.applicationRegisterNo = applicationRegisterNo;
	}

	public String getKidName() {
		return kidName;
	}

	public void setKidName(String kidName) {
		this.kidName = kidName;
	}

	public Integer getKidAge() {
		return kidAge;
	}

	public void setKidAge(Integer kidAge) {
		this.kidAge = kidAge;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	
	
}
