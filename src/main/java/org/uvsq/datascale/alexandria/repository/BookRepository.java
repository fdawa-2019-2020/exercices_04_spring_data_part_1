package org.uvsq.datascale.alexandria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uvsq.datascale.alexandria.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	public List<Book> findByTitle(String title);
	
	public List<Book> findByTitleIsContainingIgnoreCase(String text);
	

}
