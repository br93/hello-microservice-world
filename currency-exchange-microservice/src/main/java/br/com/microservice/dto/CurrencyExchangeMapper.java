package br.com.microservice.dto;

import br.com.microservice.entity.CurrencyExchange;

public class CurrencyExchangeMapper {

	public static CurrencyExchangeDTO toDTO(CurrencyExchange entity) {
		return new CurrencyExchangeDTO(entity.getId(), entity.getFrom(), entity.getTo(), entity.getConversionFactor(),
				null, null);
	}

	public static CurrencyExchange toEntity(CurrencyExchangeDTO dto) {
		return new CurrencyExchange(null, dto.getFrom(), dto.getTo(), dto.getConversionFactor());
	}
	
	
	
	

}
