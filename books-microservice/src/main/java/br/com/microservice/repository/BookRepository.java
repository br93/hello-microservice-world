package br.com.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.microservice.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
