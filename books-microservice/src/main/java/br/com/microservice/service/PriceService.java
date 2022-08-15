package br.com.microservice.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.microservice.proxy.CurrencyExchangeProxy;
import br.com.microservice.response.CurrencyExchangeDTO;

@Service
public class PriceService {

	private final WebClient currencyExchange;
	private final CurrencyExchangeProxy proxy;

	public PriceService(WebClient currencyExchange, CurrencyExchangeProxy proxy) {
		this.currencyExchange = currencyExchange;
		this.proxy = proxy;
	}

	public BigDecimal convertPriceWebClient(BigDecimal value, String oldCurrency, String newCurrency) {
		CurrencyExchangeDTO response = currencyExchange.get().uri(uriBuilder -> uriBuilder
				.path("/{value}/{oldCurrency}/{newCurrency}").build(value, oldCurrency, newCurrency)).retrieve()
				.bodyToMono(CurrencyExchangeDTO.class).block();

		return response.getConvertedValue();
	}

	public BigDecimal convertPriceFeign(BigDecimal value, String newCurrency) {
		CurrencyExchangeDTO response = getCurrency(value, newCurrency);
		return response.getConvertedValue();
	}

	public String getCurrencyPort(BigDecimal value, String newCurrency) {
		CurrencyExchangeDTO response = getCurrency(value, newCurrency);
		return response.getEnvironment();
	}

	private CurrencyExchangeDTO getCurrency(BigDecimal value, String newCurrency) {
		return proxy.getCurrencyExchange(value, "USD", newCurrency);
	}

}
