package com.haddy.microservice.currencyexchangeservice.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.haddy.microservice.currencyexchangeservice.currencyExchange.CurrencyExchange;
import com.haddy.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class currencyExchangeController {

	private Logger logger=LoggerFactory.getLogger(currencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository repository;
	@Autowired
	private org.springframework.core.env.Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchange(@PathVariable String from, @PathVariable String to) {
		logger.info("retriveExchange called with {} to {}", from,to);
		
		CurrencyExchange currencyExchange=repository.findByFromAndTo(from, to);
		
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find date"+from+"to"+to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		
		return currencyExchange;
	}
}
