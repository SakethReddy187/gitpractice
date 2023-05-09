package com.example.demo.service.impl;

import static com.example.demo.utils.Converter.convert;
import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ValidationService;
import com.example.demo.utils.CustomValidationException;
import com.example.demo.utils.ValidationServiceEnum;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ApplicationContext context;

	@Override
	public CustomerDto add(CustomerDto customerDto) {

		if(Period.between(customerDto.getBirthDate(),LocalDate.now()).getYears() < 21){
			throw new CustomValidationException(Arrays.asList("Age less than 21"));
		}
		CompletableFuture<Boolean> future1 = executeValidations(ValidationServiceEnum.SSN_VALIDATOR,
				customerDto.getSsn());
		CompletableFuture<Boolean> future2 = executeValidations(ValidationServiceEnum.EMAIL_VALIDATOR,
				customerDto.getEmail());
		CompletableFuture<Boolean> future3 = executeValidations(ValidationServiceEnum.PHONE_VALIDATOR,
				customerDto.getPhone());
		Boolean validation = Stream.of(future1, future2, future3).map(CompletableFuture::join)
				.reduce((a, b) -> Boolean.logicalAnd(a, b)).orElse(false);
		if (!validation) {
			performValidationOperations(future1, future2, future3);
		}
		Customer customer = convert(customerDto, Customer::new);
		customer = customerRepository.save(customer);
		customerDto.setCustomerId(customer.getCustomerId());
		return customerDto;
	}

	private void performValidationOperations(CompletableFuture<Boolean>... future) {
		List<String> errorMessages = new ArrayList<>(3);
		try {
			if (!future[0].get())
				errorMessages.add("Validation Failed for SSN");
			if (!future[1].get())
				errorMessages.add("Validation Failed for Email Id");
			if (!future[2].get())
				errorMessages.add("Validation Failed for Phone Number");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			errorMessages.clear();
			errorMessages.add("Some Internal Error Occured");
		}
		throw new CustomValidationException(errorMessages);
	}

	private CompletableFuture<Boolean> executeValidations(ValidationServiceEnum validationServiceEnum, String value) {
		ValidationService service = context.getBean(validationServiceEnum.getServiceName(), ValidationService.class);
		return service.validate(value);
	}

	@Override
	public CustomerDto update(CustomerDto customerDto) {
		Customer customer = customerRepository.getReferenceById(customerDto.getCustomerId());
		customer = customerRepository.save(convert(customerDto, customer));
		return convert(customer, customerDto);
	}

	@Override
	public void remove(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public CustomerDto fetch(Long customerId) {
		return convert(customerRepository.getReferenceById(customerId), CustomerDto::new);
	}

	@Override
	public List<CustomerDto> fetchAll() {
		return customerRepository.findAll().stream().map(customer -> convert(customer, CustomerDto::new))
				.collect(toList());
	}

	@Override
	public CustomerDto fetchBasisOfName(String name) {
		return convert(customerRepository.findByName(name),new CustomerDto());
	}

}
