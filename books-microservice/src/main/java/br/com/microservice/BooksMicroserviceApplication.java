package br.com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BooksMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksMicroserviceApplication.class, args);
	}

}
