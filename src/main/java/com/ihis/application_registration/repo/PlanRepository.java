package com.ihis.application_registration.repo;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ihis.application_registration.dto.PlanRequestDto;
import com.ihis.application_registration.dto.PlanResponseDto;
import com.ihis.application_registration.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

   

	PlanResponseDto save(@Valid PlanRequestDto planrequestDto);
}
