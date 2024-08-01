package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo.validation.PasswordSizeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PasswordSizeValidator.class)
public @interface ValidPasswordSize {
	
	  String message() default "invalid password length."; 

	  Class<?>[] groups() default {};
	  
	  Class<? extends Payload>[] payload() default {};

	  int min() default 12;
	  
	  int max() default 24;
}
