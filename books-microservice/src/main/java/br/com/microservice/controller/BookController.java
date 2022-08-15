package br.com.microservice.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.dto.BookDTO;
import br.com.microservice.dto.BookMapper;
import br.com.microservice.service.BookService;
import br.com.microservice.service.PriceService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("books")
public class BookController {

	private final Environment environment;
	private final BookService bookService;
	private final PriceService priceService;

	public BookController(Environment environment, BookService bookService, PriceService priceService) {
		this.environment = environment;
		this.bookService = bookService;
		this.priceService = priceService;
	}

	@GetMapping("web-client/{id}/{currency}")
	public ResponseEntity<BookDTO> findBook_webClient(@PathVariable Long id, @PathVariable String currency) {

		var book = bookService.findById(id);
		var dto = BookMapper.toDTO(book);

		var port = environment.getProperty("local.server.port");
		var price = priceService.convertPriceWebClient(book.getPrice(), "USD", currency);

		dto.setCurrency(currency);
		dto.setEnvironment(port);
		dto.setPrice(price);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("{id}/{currency}")
	@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
	public ResponseEntity<BookDTO> findBook_openFeign(@PathVariable Long id, @PathVariable String currency) {

		var book = bookService.findById(id);
		var bookDto = BookMapper.toDTO(book);

		var bookPort = environment.getProperty("local.server.port");
		var bookPrice = priceService.convertPriceFeign(book.getPrice(), currency);

		var currencyPort = priceService.getCurrencyPort(book.getPrice(), currency);

		bookDto.setCurrency(currency);
		bookDto.setEnvironment("Book port: " + bookPort + " CurrencyExchange port: " + currencyPort);
		bookDto.setPrice(bookPrice);

		return new ResponseEntity<>(bookDto, HttpStatus.OK);
	}
	
	public ResponseEntity<BookDTO> fallbackMethod(Exception e) {
		return new ResponseEntity<>(new BookDTO(), HttpStatus.OK);
	}

}
