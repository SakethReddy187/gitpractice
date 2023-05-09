package com.example.demo.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDto;
import com.example.demo.service.CustomerService;
import com.example.demo.utils.CustomValidationException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerDto> add(@Valid @RequestBody CustomerDto customerDto) {
		return ResponseEntity.status(CREATED).body(customerService.add(customerDto));
	}

	@PatchMapping
	public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.update(customerDto));
	}

	@GetMapping
	public ResponseEntity<List<CustomerDto>> fetchAll() {
		return ResponseEntity.ok(customerService.fetchAll());
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> fetch(@PathVariable @NotNull Long customerId) {
		return ResponseEntity.ok(customerService.fetch(customerId));
	}

	@GetMapping("/name/{customername}")
	public ResponseEntity<CustomerDto> fetchBasisOfName(@PathVariable @NotNull String customername) {
		return ResponseEntity.ok(customerService.fetchBasisOfName(customername));
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> remove(@PathVariable @NotNull Long customerId) {
		customerService.remove(customerId);
		return ResponseEntity.ok().build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream().map(er -> er.getDefaultMessage()).collect(toList());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CustomValidationException.class)
	public List<String> handleValidationExceptions(CustomValidationException ex) {
		return ex.getExceptionMessages();
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public List<String> handleUncheckedExceptions(RuntimeException ex) {
		return List.of(ex.getLocalizedMessage());
	}
}
