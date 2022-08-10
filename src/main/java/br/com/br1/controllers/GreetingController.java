package br.com.br1.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.br1.configuration.GreetingConfiguration;
import br.com.br1.model.Greeting;

@RestController
public class GreetingController {

	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final GreetingConfiguration configuration;
	
	public GreetingController(GreetingConfiguration configuration) {
		this.configuration = configuration;
	}

	@RequestMapping("hello")
	public Greeting greeting(@RequestParam(defaultValue = "") String name) {
		
		if (name.isEmpty())
			name = configuration.getDefaultValue();
		
		return new Greeting(counter.incrementAndGet(), String.format(template, configuration.getGreeting(), name));
	}
}
