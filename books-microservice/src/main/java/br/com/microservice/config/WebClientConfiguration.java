package br.com.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
	
	@Bean
	WebClient currencyExchange(WebClient.Builder builder) {
		
		return builder
			.baseUrl("http://localhost:8001/exchange")
			.defaultHeaders(httpHeaders -> {
				httpHeaders.addAll(createHeaders());
			})
			.build();
	}
	
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
				
		return headers;
		
	}

}
