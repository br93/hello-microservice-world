package br.com.microservice.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.microservice.response.CurrencyExchangeDTO;

@FeignClient(name = "currency-exchange-microservice")
public interface CurrencyExchangeProxy {

	@GetMapping("/exchange/{value}/{oldCurrency}/{newCurrency}")
	public CurrencyExchangeDTO getCurrencyExchange(
			@PathVariable BigDecimal value, 
			@PathVariable String oldCurrency,
			@PathVariable String newCurrency);
}
