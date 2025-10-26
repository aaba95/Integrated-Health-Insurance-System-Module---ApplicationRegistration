package com.ihis.application_registration.service;

import java.util.List;

import javax.validation.Valid;

import com.ihis.application_registration.dto.ApplicationResponseDto;
import com.ihis.application_registration.dto.PlanRequestDto;
import com.ihis.application_registration.dto.PlanResponseDto;
import com.ihis.application_registration.dto.SummaryResponseDto;
import com.ihis.application_registration.entity.ApplicationRegistration;
import com.ihis.application_registration.entity.Education;
import com.ihis.application_registration.entity.Income;
import com.ihis.application_registration.entity.Kids;
import com.ihis.application_registration.entity.Plan;


public interface ApplicationRegistrationService {

	ApplicationRegistration saveRegistration(ApplicationRegistration appRegistration);

//	List<ApplicationResponseDto> getApplications();

	List<ApplicationResponseDto> getApplications(int page, int size, String sortBy, String sortDir, String filterBy,
			String filterValue);

	String getStateNameFromAppRegNo(String applicationRegisterNo);

	Plan savePlan(Plan plan);

	ApplicationRegistration findByapplicationRegisterNo(String applicationRegisterNo);

	Income saveIncome(Income income);

	Education saveEducation(Education education);

	List<Kids> saveAllKids(List<Kids> kids);

	SummaryResponseDto getSummary(String applicationRegisterNo);

}
