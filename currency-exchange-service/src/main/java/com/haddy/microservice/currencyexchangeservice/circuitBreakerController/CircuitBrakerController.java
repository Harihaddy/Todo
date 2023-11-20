package com.haddy.microservice.currencyexchangeservice.circuitBreakerController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
public class CircuitBrakerController {

	private Logger logger=LoggerFactory.getLogger(CircuitBrakerController.class);
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api",fallbackMethod = "hardcodedResponse")
	//@CircuitBreaker(name = "sample-api",fallbackMethod = "hardcodedResponse")
	//@RateLimiter(name = "sample-api")
	@Bulkhead(name = "sample-api")
	public String sampleaip() {
		logger.info("check");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//		return forEntity.getBody();
		return "sample-api";
	}
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-Response";
	}
}
