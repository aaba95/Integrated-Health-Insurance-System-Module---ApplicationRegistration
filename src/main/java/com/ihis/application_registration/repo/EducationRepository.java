package com.ihis.application_registration.repo;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihis.application_registration.dto.EducationRequestDto;
import com.ihis.application_registration.dto.EducationResponseDto;

import com.ihis.application_registration.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Integer> {

	EducationResponseDto save(@Valid EducationRequestDto educationRequestDto);
}
