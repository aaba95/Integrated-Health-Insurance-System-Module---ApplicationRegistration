package com.ihis.application_registration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IntegratedHealthInsuranceModuleApplicationRegistrationApplication {

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	return modelMapper;	
	}
	
	public static void main(String[] args) {
		SpringApplication.run(IntegratedHealthInsuranceModuleApplicationRegistrationApplication.class, args);
	}

}
