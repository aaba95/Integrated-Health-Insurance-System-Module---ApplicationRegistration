package com.ihis.application_registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DC_KIDS_DTLS")
public class Kids {
	
	@Id
	@Column(name = "KIDS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="APPLICATION_REG_NO")
	private String applicationRegisterNo;
	
	@Column(name="SSN")
	private String ssn;

	@Column(name = "KID_NAME")
	private String kidName;
	
	@Column(name = "KID_AGE")
	private Integer kidAge;
	
	@Column(name="CITIZEN_ID")
	private Integer citizenID;


   
	
	

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

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

	public Integer getCitizenID() {
		return citizenID;
	}

	public void setCitizenID(Integer citizenID) {
		this.citizenID = citizenID;
	}
	
	
	
}
