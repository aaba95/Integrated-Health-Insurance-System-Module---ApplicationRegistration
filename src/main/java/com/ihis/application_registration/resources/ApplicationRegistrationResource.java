package com.ihis.application_registration.resources;

import java.util.ArrayList;
import java.util.List; 

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ihis.application_registration.config.ApplicationRegistrationNumberGenerator;
import com.ihis.application_registration.dto.ApplicationRequestDto;
import com.ihis.application_registration.dto.ApplicationResponseDto;
import com.ihis.application_registration.dto.EducationRequestDto;
import com.ihis.application_registration.dto.EducationResponseDto;
import com.ihis.application_registration.dto.IncomeRequestDto;
import com.ihis.application_registration.dto.IncomeResponseDto;
import com.ihis.application_registration.dto.KidsRequestDto;
import com.ihis.application_registration.dto.KidsResponseDto;
import com.ihis.application_registration.dto.PlanRequestDto;
import com.ihis.application_registration.dto.PlanResponseDto;
import com.ihis.application_registration.dto.SsaResponse;
import com.ihis.application_registration.entity.ApplicationRegistration;
import com.ihis.application_registration.entity.Education;
import com.ihis.application_registration.entity.Income;
import com.ihis.application_registration.entity.Kids;
import com.ihis.application_registration.entity.Plan;
import com.ihis.application_registration.service.ApplicationRegistrationService;

@Component
public class ApplicationRegistrationResource {
	
	@Autowired
	private ApplicationRegistration appReg;

	private ApplicationRegistrationService appRegService;

	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationRegistrationNumberGenerator appRegNogenerator;

	@Autowired
	public ApplicationRegistrationResource(ApplicationRegistrationService appRegService, ModelMapper modelMapper) {
		super();
		this.appRegService = appRegService;
		this.modelMapper = modelMapper;
	}
	
	

	// base URL of SSA-WEB-APP (make sure it's running)
	private static final String SSA_BASE_URL = "http://localhost:1112/ssa/state/{ssn}";

	public String getStateFromSsn(String ssn) {
		try {
			String url = SSA_BASE_URL;
			SsaResponse response = restTemplate.getForObject(url, SsaResponse.class, ssn);

			// Now get only the state name
			String stateName = response.getStateName();
			System.out.println(stateName);  // Prints "Texas"
			return stateName;
		} catch (HttpClientErrorException.NotFound e) {
			return "Invalid SSN";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error connecting to SSA service";
		}
	}

	public ApplicationResponseDto saveRegistration(@Valid ApplicationRequestDto request) {
		ApplicationRegistration appRegistration = modelMapper.map(request, ApplicationRegistration.class);

		String appRegNo = appRegNogenerator.generateAppRegNo();
		appRegistration.setApplicationRegisterNo(appRegNo);
		ApplicationRegistration savedApplicationReg = appRegService.saveRegistration(appRegistration);

		ApplicationResponseDto AppRegResponseDto = modelMapper.map(savedApplicationReg, ApplicationResponseDto.class);

		return AppRegResponseDto;
	}

//	public List<ApplicationResponseDto> getApplications(page,size,sortBy,sortDir,filterBy,filterValue) {
//		List<ApplicationResponseDto> list=appRegService.getApplications(page,size,sortBy,sortDir,filterBy,filterValue);
//		return list;
//	}



	public List<ApplicationResponseDto> getApplications(int page, int size, String sortBy, String sortDir,
			String filterBy, String filterValue) {
		List<ApplicationResponseDto> list=appRegService.getApplications(page,size,sortBy,sortDir,filterBy,filterValue);
		return list;
	}

	public String getStateFromAppRegNo(String applicationRegisterNo) {
		String stateName=appRegService.getStateNameFromAppRegNo(applicationRegisterNo);
		return stateName;
	}

	public PlanResponseDto savePlan(@Valid PlanRequestDto planrequestDto) {
		Plan plan = modelMapper.map(planrequestDto, Plan.class);
		
		String applicationRegisterNo = planrequestDto.getApplicationRegisterNo();
		
		ApplicationRegistration applicationRegistration=appRegService.findByapplicationRegisterNo(applicationRegisterNo);
		
		//save plan first then set plan in application registration otherwise plan save two times in plan table
		Plan savedPlan=appRegService.savePlan(plan);
		
		applicationRegistration.setPlan(plan);//set plan_id in Application Registration Table to particular applicationRegistrationId
		appRegService.saveRegistration(applicationRegistration);
		
	
		PlanResponseDto planResponseDto=modelMapper.map(savedPlan, PlanResponseDto.class);
		return planResponseDto;
	}

	public IncomeResponseDto saveIncome(@Valid IncomeRequestDto incomeRequestDto) {
    Income income = modelMapper.map(incomeRequestDto, Income.class);
    
    String applicationRegisterNo = incomeRequestDto.getApplicationRegisterNo();
    
    ApplicationRegistration applicationRegistration = appRegService.findByapplicationRegisterNo(applicationRegisterNo);
    
    //save income first then set income in application registration otherwise income save two times
  Income savedIncome=  appRegService.saveIncome(income);
  
  applicationRegistration.setIncome(income);
  appRegService.saveRegistration(applicationRegistration);
  
  IncomeResponseDto incomeResponseDto = modelMapper.map(savedIncome, IncomeResponseDto.class);
    
		return incomeResponseDto;
	}

	public EducationResponseDto saveEducation(@Valid EducationRequestDto educationRequestDto) {

		Education education = modelMapper.map(educationRequestDto, Education.class);
		
		String applicationRegisterNo = educationRequestDto.getApplicationRegisterNo();
		
		ApplicationRegistration applicationRegistration = appRegService.findByapplicationRegisterNo(applicationRegisterNo);
		
		//save education first then set education in application registration otherwise education save two times
		Education savedEducation=appRegService.saveEducation(education);
		
		applicationRegistration.setEducation(education);
		
		appRegService.saveRegistration(applicationRegistration);
		
		EducationResponseDto educationResponseDto = modelMapper.map(savedEducation, EducationResponseDto.class);
		
		return educationResponseDto;
	}

	public List<KidsResponseDto> saveAllKids(@Valid List<KidsRequestDto> kidsRequestDto) {
	//	Kids kids = modelMapper.map(kidsRequestDto, Kids.class);
		List<Kids> kidsList = new ArrayList<>();
		for (KidsRequestDto dto : kidsRequestDto) {
		    Kids map = modelMapper.map(dto, Kids.class);
		    kidsList.add(map);
		}
		String applicationRegisterNo=null;
		for(KidsRequestDto dto:kidsRequestDto) {
			 applicationRegisterNo = dto.getApplicationRegisterNo();
		}
	
	ApplicationRegistration applicationRegistration = appRegService.findByapplicationRegisterNo(applicationRegisterNo);
	
	//save kids first then set kids in application_registration_kids table otherwise kids save two times
         List<Kids> savedKids =	appRegService.saveAllKids(kidsList);
          
          applicationRegistration.setKids(savedKids);
          
          appRegService.saveRegistration(applicationRegistration);
          
          List<KidsResponseDto> kidsList2=new ArrayList<KidsResponseDto>();
          for(Kids kids:savedKids) {
        	  KidsResponseDto kidsResponseDto = modelMapper.map(kids, KidsResponseDto.class);
        	  kidsList2.add(kidsResponseDto);
          }
          
		return kidsList2;
	}}
