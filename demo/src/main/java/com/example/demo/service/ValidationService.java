package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

public interface ValidationService {
	CompletableFuture<Boolean> validate(String value);
}
