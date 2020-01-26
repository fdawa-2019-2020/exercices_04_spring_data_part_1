package org.uvsq.datascale.alexandria.integration;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.uvsq.datascale.alexandria.repository.EditionRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTestIT {

	
	@Autowired
	BookRepository bookRepository;
	@Autowired
	EditionRepository editionRepository;
	
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
		assertThat(editionRepository.count()).isEqualTo(1);
	}
	
	
	
	
	
	
	@Test
	public void createBookWithSameEditions() throws ParseException {
		// given
		Book book = new Book();
		book.setTitle("Foo bar recettes");
		// first add
		{
			Edition edition = new Edition();
			edition.setAvailable(true);
			edition.setEditorName("FooBarEdition");
			edition.setParutionDate(simpleDateFormat.parse("2019-02-02"));
			edition.setNumberOfPage(123);
			// when
			book.getEditions().add(edition);
		}
		// 2nd add
		{
			Edition edition = new Edition();
			edition.setAvailable(true);
			edition.setEditorName("FooBarEdition");
			edition.setParutionDate(simpleDateFormat.parse("2019-02-02"));
			edition.setNumberOfPage(123);
			// when
			book.getEditions().add(edition);			
		}
		
		assertThat(book.getEditions().size()).isEqualTo(1);
		// then
		bookRepository.saveAndFlush(book);
		
		assertThat(editionRepository.count()).isEqualTo(1);
	}

}
