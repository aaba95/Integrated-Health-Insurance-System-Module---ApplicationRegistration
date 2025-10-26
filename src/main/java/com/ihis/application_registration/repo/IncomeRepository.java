package com.ihis.application_registration.repo;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihis.application_registration.dto.IncomeRequestDto;
import com.ihis.application_registration.dto.IncomeResponseDto;

import com.ihis.application_registration.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

	IncomeResponseDto save(@Valid IncomeRequestDto incomeRequestDto);
	
}
