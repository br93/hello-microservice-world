package br.com.microservice.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.config.GreetingConfiguration;
import br.com.microservice.model.Greeting;

@RestController
@RequestMapping("hello")
public class GreetingController {

	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final GreetingConfiguration configuration;

	public GreetingController(GreetingConfiguration configuration) {
		this.configuration = configuration;
	}

	@GetMapping
	public Greeting greeting(@RequestParam(defaultValue = "") String name) {

		if (name.isEmpty())
			name = configuration.getDefaultValue();

		return new Greeting(counter.incrementAndGet(), String.format(template, configuration.getGreeting(), name));
	}
}
