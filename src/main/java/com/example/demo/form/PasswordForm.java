package com.example.demo.form;

import com.example.demo.annotation.ValidPasswordSize;

public class PasswordForm {

	@ValidPasswordSize
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
