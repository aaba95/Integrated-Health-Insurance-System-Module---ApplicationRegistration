package com.ihis.application_registration.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihis.application_registration.entity.Kids;

@Repository
public interface KidsRepository extends JpaRepository<Kids, Integer> {

	//List<Kids> saveAll(Kids kids);

}
