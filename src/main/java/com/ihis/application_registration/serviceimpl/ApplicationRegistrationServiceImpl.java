package com.ihis.application_registration.serviceimpl;

import java.util.List; 
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ihis.application_registration.config.ApplicationSpecification;
import com.ihis.application_registration.dto.ApplicationResponseDto;
import com.ihis.application_registration.entity.ApplicationRegistration;
import com.ihis.application_registration.repo.ApplicationRegistrationRepository;
import com.ihis.application_registration.service.ApplicationRegistrationService;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService {

	
	
	private ApplicationRegistrationRepository appRegRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public ApplicationRegistrationServiceImpl(ApplicationRegistrationRepository appRegRepo) {
		super();
		this.appRegRepo = appRegRepo;
	}
	
	@Override
	public ApplicationRegistration saveRegistration(ApplicationRegistration appRegistration) {
		ApplicationRegistration saveApplicationReg = appRegRepo.save(appRegistration);
		return saveApplicationReg;
	}
	
//	@Override
//	public List<ApplicationResponseDto> getApplications() {
//		List<ApplicationRegistration> listOfApplication = appRegRepo.findAll();
//		 List<ApplicationResponseDto> listOfApplicationResponseDto = listOfApplication.stream()
//			        .map(app -> modelMapper.map(app, ApplicationResponseDto.class))
//			        .collect(Collectors.toList());
//
//			    return listOfApplicationResponseDto;
//	}
	
	@Override
	public List<ApplicationResponseDto> getApplications(int page, int size, String sortBy, String sortDir,
			String filterBy, String filterValue) {
		
		  // Sorting
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
                
                
//                Specification<ApplicationRegistration> spec = ApplicationSpecification.filterByStateAndGender(filterBy, filterValue);
//                
//                if(spec!=null) {
//                	appRegRepo.findAll(spec);
//                }
                
                // Pagination
                Pageable pageable = PageRequest.of(page, size, sort);

               // Page<ApplicationRegistration> pageResult = null;
                
                Page<ApplicationRegistration> pageResult = appRegRepo.findAll(pageable);
                
            
                
             // Map entity page to DTO page
                Page<ApplicationResponseDto> dtoPage = pageResult.map(app -> modelMapper.map(app, ApplicationResponseDto.class));

                List<ApplicationResponseDto> content = dtoPage.getContent();
                return content;
		
	}
}
