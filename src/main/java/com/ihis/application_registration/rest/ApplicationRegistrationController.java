package com.ihis.application_registration.rest;

import java.util.List; 

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.ihis.application_registration.dto.SummaryResponseDto;
import com.ihis.application_registration.entity.ApplicationRegistration;
import com.ihis.application_registration.entity.Education;
import com.ihis.application_registration.resources.ApplicationRegistrationResource;
import com.ihis.application_registration.service.ApplicationRegistrationService;

@RestController
@RequestMapping(value = "/registration")
public class ApplicationRegistrationController {

	@Autowired
	private ApplicationRegistrationService appRegService;
	
	private ApplicationRegistrationResource appRegResource;

	@Autowired
	public ApplicationRegistrationController(ApplicationRegistrationResource appRegResource) {
		super();
		this.appRegResource = appRegResource;
	}
	
	 @GetMapping(value = "summary/{applicationRegisterNo}")
	 public ResponseEntity<?> getSummary(@PathVariable String applicationRegisterNo){
		 SummaryResponseDto summaryResponseDto=appRegService.getSummary(applicationRegisterNo);
		 
		 if(summaryResponseDto!=null) {
			 return new ResponseEntity<>(summaryResponseDto,HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>("No Records found for this application registration Number",HttpStatus.CONFLICT);
		 }
	 }
	
	//save plan 
	@PostMapping(value = "/plan")
	public ResponseEntity<?> addPlan(@Valid @RequestBody PlanRequestDto PlanrequestDto){
		String applicationRegisterNo = PlanrequestDto.getApplicationRegisterNo();
		String stateFromAppRegNo = appRegResource.getStateFromAppRegNo(applicationRegisterNo);
		//System.out.println(stateFromAppRegNo);
		if(stateFromAppRegNo.equals("New Jersy")) {
			PlanResponseDto response=appRegResource.savePlan(PlanrequestDto);
		
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Plan applicable for only New Jesrsy State",HttpStatus.CONFLICT);
		}
		
		
	}
	
	//save income
	@PostMapping(value = "/income")
	public ResponseEntity<?> addIncome(@Valid @RequestBody IncomeRequestDto incomeRequestDto){
		//incomeRequestDto.setApplicationRegisterNo(applicationRegisterNo);
		IncomeResponseDto incomeResponseDto=appRegResource.saveIncome(incomeRequestDto);
		String applicationRegisterNo = incomeRequestDto.getApplicationRegisterNo();
		String stateFromAppRegNo = appRegResource.getStateFromAppRegNo(applicationRegisterNo);
		if(stateFromAppRegNo.equals("New Jersy")) {
			return new ResponseEntity<>(incomeResponseDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("applicant not belong to  New Jesrsy State",HttpStatus.CONFLICT);
		}	
	}
	
	//save education
	@PostMapping(value = "/education")
	public ResponseEntity<?> addEducation(@Valid @RequestBody EducationRequestDto educationRequestDto){
		EducationResponseDto educationResponseDto=appRegResource.saveEducation(educationRequestDto);
		String applicationRegisterNo = educationRequestDto.getApplicationRegisterNo();
		
		String stateFromAppRegNo = appRegResource.getStateFromAppRegNo(applicationRegisterNo);
		if(stateFromAppRegNo.equals("New Jersy")) {
			return new ResponseEntity<>(educationResponseDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("applicant not belong to  New Jesrsy State",HttpStatus.CONFLICT);
		}	
	}
	
	//save kids
	@PostMapping(value = "/kids")
	public ResponseEntity<?> addKids(@Valid @RequestBody List<KidsRequestDto> kidsRequestDto){
		List<KidsResponseDto> kidsResponseDto=appRegResource.saveAllKids(kidsRequestDto);
		String applicationRegisterNo=null;
		for(KidsRequestDto dto:kidsRequestDto) {
			 applicationRegisterNo = dto.getApplicationRegisterNo();
		}
		//String applicationRegisterNo = kidsRequestDto.getApplicationRegisterNo();
		
		String stateFromAppRegNo = appRegResource.getStateFromAppRegNo(applicationRegisterNo);
		if(stateFromAppRegNo.equals("New Jersy")) {
			return new ResponseEntity<>(kidsResponseDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("applicant not belong to  New Jesrsy State",HttpStatus.CONFLICT);
		}	
		
	}
	
	//save application registration
	@PostMapping
	public ResponseEntity<ApplicationResponseDto> addRegistration(@Valid @RequestBody ApplicationRequestDto request){
		String stateFromSsn = appRegResource.getStateFromSsn(request.getSsn());
		request.setStateName(stateFromSsn);
	//	System.out.println(request.getSsn());
ApplicationResponseDto response=appRegResource.saveRegistration(request);
return new ResponseEntity<ApplicationResponseDto>(response,HttpStatus.CREATED);
	}
	
	//view all applications
	@GetMapping
	public ResponseEntity<List<ApplicationResponseDto>> getApplications( 
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String filterBy,
            @RequestParam(required = false) String filterValue){
		List<ApplicationResponseDto> listOfApplications=appRegResource.getApplications(page,size,sortBy,sortDir,filterBy,filterValue);
		return new ResponseEntity<List<ApplicationResponseDto>>(listOfApplications,HttpStatus.OK);
	}
	
}
