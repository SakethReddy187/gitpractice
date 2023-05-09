package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	  public ThreadPoolTaskExecutor validationExecutor() {
	    ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
	    poolExecutor.setCorePoolSize(3);
	    poolExecutor.setMaxPoolSize(6);
	    poolExecutor.setQueueCapacity(10);
	    poolExecutor.setThreadNamePrefix("validationExecutor");
	    poolExecutor.initialize();
	    return poolExecutor;
	  }

}
