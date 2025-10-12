package com.ihis.application_registration.config;

import org.springframework.data.jpa.domain.Specification;

import com.ihis.application_registration.entity.ApplicationRegistration;

public class ApplicationSpecification {

	public static Specification<ApplicationRegistration> filterByStateAndGender(String filterBy, String filterValue) {
        return (root, query, cb) -> {
            if (filterBy == null || filterValue == null || filterValue.trim().isEmpty()) {
                return cb.conjunction(); // no filter applied
            }

            String value = filterValue.trim().toLowerCase();

            switch (filterBy.toLowerCase()) {
                case "statename":
                    return cb.like(cb.lower(root.get("stateName")), "%" + value + "%");

                case "gender":
                    return cb.equal(cb.lower(root.get("gender")), value);

                default:
                    return cb.conjunction(); // no filtering if invalid field
            }
        };
    }
}
