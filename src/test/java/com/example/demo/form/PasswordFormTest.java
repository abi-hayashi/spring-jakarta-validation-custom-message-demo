package com.example.demo.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest
class PasswordFormTest {
	
	@Autowired
	private Validator validator;

	@Test
	void testValidate() throws Exception {
	
		PasswordForm form = new PasswordForm();
		form.setPassword("123");
		
		Set<ConstraintViolation<PasswordForm>> violations = validator.validate(new PasswordForm());
	
		assertEquals(1, violations.size());
		
		ConstraintViolation<PasswordForm> cv = violations.iterator().next();

		System.out.println(cv.getMessage());
	}

}
