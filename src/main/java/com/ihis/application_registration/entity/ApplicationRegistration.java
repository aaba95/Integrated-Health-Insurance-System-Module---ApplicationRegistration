package com.ihis.application_registration.entity;

import java.util.List; 

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;



@Entity
@Table(name = "Application_Registration_DTLS")
@Component
public class ApplicationRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="MOBILE_NO")
	private Long mobileNo;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name="SSN")
	private String ssn;
	
	@Column(name="DOB")
	private String dob;
	
	@Column(name="CITIZEN_ID")
	private Integer citizenID;
	
	@Column(name="APPLICATION_REG_NO")
	private String applicationRegisterNo;
	
	@Column(name = "STATE_NAME")
	private String stateName;
	
  @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn(name = "plan_id" , referencedColumnName = "PLAN_ID") // FK column in this table
private Plan plan;
	
	@OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "income_id" , referencedColumnName = "INCOME_ID") // FK column in this table
	private Income income;
	
    // ✅ One application can have many kids
    @OneToMany(cascade =CascadeType.ALL)
    @JoinTable(name = "APPLICATION_REGISTRATION_KIDS")
    private List<Kids> kidsList; 
	
	@OneToOne
	@JoinColumn(name = "education_id" , referencedColumnName = "EDUCATION_ID") // FK column in this table
	private Education education;
	
	

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	
	public List<Kids> getKids() {
		return kidsList;
	}

	public void setKids(List<Kids> kidsList) {
		this.kidsList = kidsList;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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
	
	
	
	
	
	
}
