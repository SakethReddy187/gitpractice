package com.example.demo.utils;

public enum ValidationServiceEnum {
	EMAIL_VALIDATOR("EmailValidationService"), PHONE_VALIDATOR("PhoneValidationService"),
	SSN_VALIDATOR("SsnValidationService");

	private String serviceName;

	ValidationServiceEnum(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	void SSN_VALIDATOR(String ssn) {
		// TODO Auto-generated method stub
		
	}
}
