package com.ihis.application_registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DC_EDUCATION_DTLS")
public class Education {

	@Id
	@Column(name = "EDUCATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="APPLICATION_REG_NO")
	private String applicationRegisterNo;
	
	@Column(name = "DEGREE")
	private String degree;
	
	@Column(name = "PASS_YEAR")
	private Integer passingYear;
	
	@Column(name = "UNIVERSITY")
	private String universityName;

	
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
