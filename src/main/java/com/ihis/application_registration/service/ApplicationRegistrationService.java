package com.ihis.application_registration.service;

import java.util.List;

import com.ihis.application_registration.dto.ApplicationResponseDto;
import com.ihis.application_registration.entity.ApplicationRegistration;

public interface ApplicationRegistrationService {

	ApplicationRegistration saveRegistration(ApplicationRegistration appRegistration);

//	List<ApplicationResponseDto> getApplications();

	List<ApplicationResponseDto> getApplications(int page, int size, String sortBy, String sortDir, String filterBy,
			String filterValue);

}
