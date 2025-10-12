package com.ihis.application_registration.config;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class ApplicationRegistrationNumberGenerator {

	private static final String PREFIX = "ihis";
    private static final int DIGITS = 4;
    private static final int LETTERS = 3;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String LETTERS_POOL = "abcdefghijklmnopqrstuvwxyz";

    public String generateAppRegNo() {
        StringBuilder sb = new StringBuilder(PREFIX);

        // digits
        int max = (int) Math.pow(10, DIGITS);
        int number = RANDOM.nextInt(max); // 0 .. max-1
        sb.append(String.format("%0" + DIGITS + "d", number));

        // letters
        for (int i = 0; i < LETTERS; i++) {
            sb.append(LETTERS_POOL.charAt(RANDOM.nextInt(LETTERS_POOL.length())));
        }
        return sb.toString();
    }
	
}
