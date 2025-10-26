package com.ihis.application_registration.dto;

import javax.validation.constraints.NotBlank;

public class EducationRequestDto {

	@NotBlank(message = "Degree should not be blank")
	private String degree;
	

	private Integer passingYear;
	
	
	private String universityName;
	
	@NotBlank(message = "Application Registration Number Not be Blank")
	private String applicationRegisterNo;

	public String getApplicationRegisterNo() {
		return applicationRegisterNo;
	}


	public void setApplicationRegisterNo(String applicationRegisterNo) {
		this.applicationRegisterNo = applicationRegisterNo;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public Integer getPassingYear() {
		return passingYear;
	}


	public void setPassingYear(Integer passingYear) {
		this.passingYear = passingYear;
	}


	public String getUniversityName() {
		return universityName;
	}


	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
	
	
}
