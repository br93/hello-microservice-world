package br.com.microservice.config;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		Function<PredicateSpec, Buildable<Route>> get = p -> p.path("/get").uri("http://httpbin.org:80");
		Function<PredicateSpec, Buildable<Route>> helloWorld = p -> p.path("/hello/**").uri("lb://hello-world");
		Function<PredicateSpec, Buildable<Route>> currencyExchange = p -> p.path("/exchange/**").uri("lb://currency-exchange-microservice");
		Function<PredicateSpec, Buildable<Route>> bookStore = p -> p.path("/books/**").uri("lb://books-microservice");
		
		return builder.routes()
				.route(get)
				.route(helloWorld)
				.route(currencyExchange)
				.route(bookStore)
				.build();
	}
}
