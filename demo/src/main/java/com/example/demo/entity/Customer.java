package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long customerId;
	private String name;
	private String ssn;
	private String phone;
	private String email;
	private LocalDate birthDate;
}
