package com.ihis.application_registration.serviceimpl;

import java.util.List;  
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ihis.application_registration.config.ApplicationSpecification;
import com.ihis.application_registration.dto.ApplicationResponseDto;
import com.ihis.application_registration.dto.EducationResponseDto;
import com.ihis.application_registration.dto.IncomeResponseDto;
import com.ihis.application_registration.dto.KidsResponseDto;
import com.ihis.application_registration.dto.PlanRequestDto;
import com.ihis.application_registration.dto.PlanResponseDto;
import com.ihis.application_registration.dto.SummaryResponseDto;
import com.ihis.application_registration.entity.ApplicationRegistration;
import com.ihis.application_registration.entity.Education;
import com.ihis.application_registration.entity.Income;
import com.ihis.application_registration.entity.Kids;
import com.ihis.application_registration.entity.Plan;
import com.ihis.application_registration.repo.ApplicationRegistrationRepository;
import com.ihis.application_registration.repo.EducationRepository;
import com.ihis.application_registration.repo.IncomeRepository;
import com.ihis.application_registration.repo.KidsRepository;
import com.ihis.application_registration.repo.PlanRepository;
import com.ihis.application_registration.service.ApplicationRegistrationService;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService {

	Logger log=LoggerFactory.getLogger(ApplicationRegistrationServiceImpl.class);
	@Autowired
	private KidsRepository kidsRepo;
	
	@Autowired
	private EducationRepository educationRepo;
	
	@Autowired
	private PlanRepository planRepo;
	
	@Autowired
	private IncomeRepository incomeRepo;
	
	private ApplicationRegistrationRepository appRegRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public ApplicationRegistrationServiceImpl(ApplicationRegistrationRepository appRegRepo) {
		super();
		this.appRegRepo = appRegRepo;
	}
	
	@Override
	public ApplicationRegistration findByapplicationRegisterNo(String applicationRegisterNo) {
		// TODO Auto-generated method stub
		return appRegRepo.findByapplicationRegisterNo(applicationRegisterNo);
	}
	
	@Override
	public List<Kids> saveAllKids(List<Kids> kidsList) {
		List<Kids> response=kidsRepo.saveAll(kidsList);
		return response;
	}
	
	@Override
	public Education saveEducation(Education education) {
		Education response = educationRepo.save(education);
		return response;
	}
	
	
	@Override
	public Income saveIncome(Income income) {
		Income response = incomeRepo.save(income);
		return response;
	}
	
	@Override
	public Plan savePlan(@Valid Plan plan) {
		Plan response=planRepo.save(plan);
		return response;
	}
	
	@Override
	public String getStateNameFromAppRegNo(String applicationRegisterNo) {
		String stateName=appRegRepo.getStateNameByapplicationRegisterNo(applicationRegisterNo);
		return stateName;
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
		
		  // Sorting http://localhost:1113/registration?sortBy=name&sortDir=AsC
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
                
                
//                Specification<ApplicationRegistration> spec = ApplicationSpecification.filterByStateAndGender(filterBy, filterValue);
//                
//                if(spec!=null) {
//                	appRegRepo.findAll(spec);
//                }
                
                // Pagination http://localhost:1113/registration?page=0&size=2
                Pageable pageable = PageRequest.of(page, size, sort);

               // Page<ApplicationRegistration> pageResult = null;
                
                // Filtering (using Specification) http://localhost:1113/registration?filterBy=stateName&filterValue=New%20Jersy
                Specification<ApplicationRegistration> spec =
                        ApplicationSpecification.filterByStateAndGender(filterBy, filterValue);
                
                Page<ApplicationRegistration> pageResult = appRegRepo.findAll(spec,pageable);
                
            
                
             // Map entity page to DTO page
                Page<ApplicationResponseDto> dtoPage = pageResult.map(app -> modelMapper.map(app, ApplicationResponseDto.class));

                List<ApplicationResponseDto> content = dtoPage.getContent();
                return content;
		
	}
	
	
	@Override
	public SummaryResponseDto getSummary(String applicationRegisterNo) {
		SummaryResponseDto summary=new SummaryResponseDto();
		
		ApplicationRegistration applicationRegistration = appRegRepo.findByapplicationRegisterNo(applicationRegisterNo);
		
		 if (applicationRegistration == null) {
	            throw new RuntimeException("No record found for application: " + applicationRegisterNo);
	        }
		 
		 summary.setApplicationRegisterNo(applicationRegistration.getApplicationRegisterNo());
		 summary.setCitizenID(applicationRegistration.getCitizenID());
		 summary.setEmail(applicationRegistration.getEmail());
		 summary.setName(applicationRegistration.getName());
		 summary.setMobileNo(applicationRegistration.getMobileNo());
		 summary.setStateName(applicationRegistration.getStateName());
		 
		 log.info("Application Registration Service Implementation class   ::: "+summary.getName());
		 
		 //fetch each related entity
		 if(applicationRegistration.getPlan()!=null) {
		 summary.setPlanDetails(
		            modelMapper.map(applicationRegistration.getPlan(), PlanResponseDto.class)
		        );
		 }
		 
       if(applicationRegistration.getIncome()!=null) {
		 summary.setIncomeDetails(
		            modelMapper.map(applicationRegistration.getIncome(), IncomeResponseDto.class)
		        );
       }
		 
       if(applicationRegistration.getEducation()!=null) {
		 summary.setEducationDetails(
		            modelMapper.map(applicationRegistration.getEducation(), EducationResponseDto.class)
		        );
       }
		 
       if(applicationRegistration.getKids() != null && !applicationRegistration.getKids().isEmpty()) {
		   List<KidsResponseDto> kidsDtos = applicationRegistration.getKids()
	                .stream()
	                .map(k -> modelMapper.map(k, KidsResponseDto.class))
	                .collect(Collectors.toList());
	        summary.setKidsDetails(kidsDtos);
       }
		return summary;
	}
}
