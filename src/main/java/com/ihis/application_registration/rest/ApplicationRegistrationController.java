package com.ihis.application_registration.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihis.application_registration.dto.ApplicationRequestDto;
import com.ihis.application_registration.dto.ApplicationResponseDto;
import com.ihis.application_registration.resources.ApplicationRegistrationResource;

@RestController
@RequestMapping(value = "/registration")
public class ApplicationRegistrationController {

	
	private ApplicationRegistrationResource appRegResource;

	@Autowired
	public ApplicationRegistrationController(ApplicationRegistrationResource appRegResource) {
		super();
		this.appRegResource = appRegResource;
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
