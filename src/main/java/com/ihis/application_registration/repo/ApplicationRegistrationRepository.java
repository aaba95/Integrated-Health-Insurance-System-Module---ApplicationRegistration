package com.ihis.application_registration.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihis.application_registration.entity.ApplicationRegistration;

@Repository
public interface ApplicationRegistrationRepository extends JpaRepository<ApplicationRegistration, Integer> {

	


}
