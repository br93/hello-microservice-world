package br.com.microservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.microservice.entity.CurrencyExchange;
import br.com.microservice.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

	private final CurrencyExchangeRepository currencyExchangeRepository;

	public CurrencyExchangeService(CurrencyExchangeRepository currencyExchangeRepository) {
		this.currencyExchangeRepository = currencyExchangeRepository;
	}

	public CurrencyExchange findByFromAndTo(String from, String to) {
		Optional<CurrencyExchange> optional = currencyExchangeRepository.findByFromAndTo(from, to);

		return optional.orElseThrow(() -> new RuntimeException("Currency unsupported"));
	}

	public BigDecimal calculateConvertedValue(BigDecimal conversionFactor, BigDecimal value) {
		return value.multiply(conversionFactor).setScale(2, RoundingMode.CEILING);
	}

	

}
