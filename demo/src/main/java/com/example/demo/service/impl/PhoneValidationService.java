package com.example.demo.service.impl;

import static java.util.concurrent.CompletableFuture.completedFuture;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.service.ValidationService;

@Service("PhoneValidationService")
public class PhoneValidationService implements ValidationService {

	@Override
	@Async("validationExecutor")
	public CompletableFuture<Boolean> validate(String value) {
		try {
			Thread.sleep(2100);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return completedFuture(false);
		}
		return completedFuture(true);
	}

}
