package br.com.microservice.dto;

import br.com.microservice.entity.Book;

public class BookMapper {

	public static BookDTO toDTO(Book entity) {
		return new BookDTO(entity.getId(), entity.getTitle(), entity.getAuthor(),
				entity.getPrice(), null, null);
	}

	public static Book toEntity(BookDTO dto) {
		return new Book(null, dto.getTitle(), dto.getAuthor(), dto.getPrice());
	}
	
	

}
