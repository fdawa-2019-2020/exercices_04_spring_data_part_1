package org.uvsq.datascale.alexandria.integration;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uvsq.datascale.alexandria.domain.Book;
import org.uvsq.datascale.alexandria.domain.Edition;
import org.uvsq.datascale.alexandria.repository.BookRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTestIT {

	
	@Autowired
	BookRepository bookRepository;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	
	
	@Test
	public void createBook() throws ParseException {
		// given
		Book book = new Book();
		book.setTitle("Foo bar recettes");
		Edition edition = new Edition();
		edition.setAvailable(true);
		edition.setEditorName("FooBarEdition");
		edition.setParutionDate(simpleDateFormat.parse("2019-02-02"));
		edition.setNumberOfPage(123);
		// when
		book.getEditions().add(edition);
		// then
		bookRepository.saveAndFlush(book);
	}
}
