package com.ihis.application_registration.resources;

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
import com.ihis.application_registration.dto.SsaResponse;
import com.ihis.application_registration.entity.ApplicationRegistration;
import com.ihis.application_registration.service.ApplicationRegistrationService;

@Component
public class ApplicationRegistrationResource {

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
	}}
