package com.ihis.application_registration.dto;

import java.util.List;

public class SummaryResponseDto {

	private String name;
	
	private String email;
	
	private Long mobileNo;
	
	private Integer citizenID;
	
	private String applicationRegisterNo;
	
	private String stateName;
	
	
	
	private PlanResponseDto planDetails;
	
	private IncomeResponseDto incomeDetails;
	
	private List<KidsResponseDto> kidsDetails;
	
	private EducationResponseDto educationDetails;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public PlanResponseDto getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(PlanResponseDto planDetails) {
		this.planDetails = planDetails;
	}

	public IncomeResponseDto getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(IncomeResponseDto incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	public List<KidsResponseDto> getKidsDetails() {
		return kidsDetails;
	}

	public void setKidsDetails(List<KidsResponseDto> kidsDetails) {
		this.kidsDetails = kidsDetails;
	}

	public EducationResponseDto getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(EducationResponseDto educationDetails) {
		this.educationDetails = educationDetails;
	}
	
	
	
	
	
}
