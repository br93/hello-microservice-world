package br.com.microservice.dto;

import java.math.BigDecimal;

public class BookDTO {

	private Long id;
	private String title;
	private String author;
	private BigDecimal price;
	private String currency;
	private String environment;

	public BookDTO() {
	}

	public BookDTO(Long id, String title, String author, BigDecimal price, String currency, String environment) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.currency = currency;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
