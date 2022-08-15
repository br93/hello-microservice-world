package br.com.microservice.controller;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.dto.CurrencyExchangeDTO;
import br.com.microservice.dto.CurrencyExchangeMapper;
import br.com.microservice.service.CurrencyExchangeService;

@RestController
@RequestMapping("exchange")
public class CurrencyExchangeController {

	private Environment environment;
	private CurrencyExchangeService currencyExchangeService;

	public CurrencyExchangeController(Environment environment, CurrencyExchangeService currencyExchangeService) {
		this.environment = environment;
		this.currencyExchangeService = currencyExchangeService;
	}

	@GetMapping("{value}/{oldCurrency}/{newCurrency}")
	public CurrencyExchangeDTO getExchangeCurrency(@PathVariable BigDecimal value, @PathVariable String oldCurrency,
			@PathVariable String newCurrency) {

		var currencyExchange = currencyExchangeService.findByFromAndTo(oldCurrency, newCurrency);
		var conversionFactor = currencyExchange.getConversionFactor();
		
		var newValue = currencyExchangeService.calculateConvertedValue(conversionFactor, value);
		var port = environment.getProperty("local.server.port");
				
		var dto = CurrencyExchangeMapper.toDTO(currencyExchange);
		dto.setConvertedValue(newValue);
		dto.setEnvironment(port);
		
		return dto;

	}

}
