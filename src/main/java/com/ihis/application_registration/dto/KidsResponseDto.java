package com.ihis.application_registration.dto;



public class KidsResponseDto {

	private String kidName;

	private Integer kidAge;

	private String ssn;

	private Integer citizenID;
	
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
