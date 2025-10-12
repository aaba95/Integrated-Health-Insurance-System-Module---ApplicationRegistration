package com.ihis.application_registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfiguration {

	@Bean
	public Docket getDocket()
	{
	return new Docket(DocumentationType.OAS_30)	
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.ihis.application_registration.rest"))
			.paths(PathSelectors.any())
			.build();
	}
}
