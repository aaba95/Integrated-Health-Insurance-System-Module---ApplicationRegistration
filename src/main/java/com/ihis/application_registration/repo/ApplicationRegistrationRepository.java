package com.ihis.application_registration.repo;

import javax.validation.Valid; 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihis.application_registration.config.ApplicationSpecification;
import com.ihis.application_registration.dto.PlanRequestDto;
import com.ihis.application_registration.dto.PlanResponseDto;
import com.ihis.application_registration.entity.ApplicationRegistration;


@Repository
public interface ApplicationRegistrationRepository extends JpaRepository<ApplicationRegistration, Integer>,JpaSpecificationExecutor<ApplicationRegistration> {

	 @Query("SELECT a.stateName FROM ApplicationRegistration a WHERE a.applicationRegisterNo = :applicationRegisterNo")
		String getStateNameByapplicationRegisterNo(String applicationRegisterNo);

	ApplicationRegistration findByapplicationRegisterNo(String applicationRegisterNo);
}
