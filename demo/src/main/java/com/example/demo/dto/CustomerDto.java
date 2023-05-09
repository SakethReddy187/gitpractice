package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDto {
	private Long customerId;
	@NotBlank(message = "Customer Name is required")
	private String name;
	@NotBlank(message = "SSN is required")
	@Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}",message = "SSN is invalid")
	private String ssn;
	@NotBlank(message = "Phone Number is required")
	private String phone;
	@NotBlank(message = "Email Id is required")
	@Email(message = "Email is Invalid")
	private String email;
	@NotNull
	private LocalDate birthDate;
}
