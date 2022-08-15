package br.com.microservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.microservice.entity.Book;
import br.com.microservice.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book findById(Long id) {
		Optional<Book> optional = bookRepository.findById(id);

		return optional.orElseThrow(() -> new RuntimeException("Book not found"));
	}
	
	

}
