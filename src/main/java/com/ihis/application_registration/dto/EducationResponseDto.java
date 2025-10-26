package com.ihis.application_registration.dto;

import javax.validation.constraints.NotBlank;

public class EducationResponseDto {

	
private String degree;
	

	private Integer passingYear;
	
	
	private String universityName;
	
	
	private String applicationRegisterNo;


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


	public String getApplicationRegisterNo() {
		return applicationRegisterNo;
	}


	public void setApplicationRegisterNo(String applicationRegisterNo) {
		this.applicationRegisterNo = applicationRegisterNo;
	}
	
	
	
	
}
