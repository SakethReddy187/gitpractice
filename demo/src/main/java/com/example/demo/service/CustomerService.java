package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CustomerDto;

public interface CustomerService {
	CustomerDto add(CustomerDto customerDto);
	CustomerDto update(CustomerDto customerDto);
	void remove(Long customerId);
	CustomerDto fetch(Long customerId);
	List<CustomerDto> fetchAll();

	CustomerDto fetchBasisOfName(String name);
}
