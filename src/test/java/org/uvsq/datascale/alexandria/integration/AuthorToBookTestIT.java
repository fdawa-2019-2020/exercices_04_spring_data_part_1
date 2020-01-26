package org.uvsq.datascale.alexandria.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uvsq.datascale.alexandria.domain.Author;
import org.uvsq.datascale.alexandria.domain.Book;
import org.uvsq.datascale.alexandria.repository.AuthorRepository;
import org.uvsq.datascale.alexandria.repository.BookRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AuthorToBookTestIT {

	@Autowired 
	BookRepository bookRepository;
	@Autowired 
	AuthorRepository authorRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	public void AddSeveralAuthorToOneBook() {
		List<Author> authors = createAuthors(3);
		Book book = new Book();
		book.setTitle("Foo Bar");
		for (Author author : authors) {
			author.getBooks().add(book);
			book.getAuthors().add(author);
			authorRepository.save(author);
		}
		bookRepository.saveAndFlush(book);
		
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book", Integer.class)).isEqualTo(1);
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM author", Integer.class)).isEqualTo(3);
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book_authors", Integer.class)).isEqualTo(3);
	}

	@Test
	public void AddSeveralBooksToOneAuthor() {
		
		// given 
		Author author = new Author();
		author.setLastname("John");
		author.setFirstname("DOE");
		
		Arrays.stream(new String[] {"Foo", "Bar", "Ooops"}).forEach( title -> {
			Book book = new Book();
			book.setTitle(title);
			author.getBooks().add(book);
			book.getAuthors().add(author);
			bookRepository.save(book);
		});

		// when
		authorRepository.saveAndFlush(author);
		
		// then
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book", Integer.class)).isEqualTo(3);
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM author", Integer.class)).isEqualTo(1);
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book_authors", Integer.class)).isEqualTo(3);
	}
	
	
	@Test
	public void AddSeveralBooksToOneAuthorThenRemoveOne() {
		
		// given 
		Author author = new Author();
		author.setLastname("John");
		author.setFirstname("DOE");
		
		Arrays.stream(new String[] {"Foo", "Bar", "Ooops"}).forEach( title -> {
			Book book = new Book();
			book.setTitle(title);
			author.getBooks().add(book);
			book.getAuthors().add(author);
			bookRepository.save(book);
		});

		Author retrievedAuthor = authorRepository.saveAndFlush(author);
		
		// When		
		Book book = retrievedAuthor.getBooks()
				.stream()
				.filter(b -> b.getTitle().equalsIgnoreCase("ooops"))
				.findFirst()
				.get();
		
		retrievedAuthor.getBooks().remove(book);
		book.getAuthors().remove(retrievedAuthor);
		bookRepository.save(book);
		authorRepository.saveAndFlush(retrievedAuthor);
		
		
		// then
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book", Integer.class)).isEqualTo(3);
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM author", Integer.class)).isEqualTo(1);
		assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book_authors", Integer.class)).isEqualTo(2);
	}
	
	
	
	private List<Author> createAuthors(int max) {
		String[][] firstAndLastnames =  getFirstAndLastnames();
		List<Author> authors = new LinkedList<>();
		for (int i = 0; i < firstAndLastnames.length && i < max; i++) {
			String[] name = firstAndLastnames[i];
			String firstname = name[0];
			String lastname  = name[1];
			Author author = new Author();
			author.setLastname(lastname);
			author.setFirstname(firstname);
			authors.add(author);
		}
		return authors;
	}
	
	private static String[][] getFirstAndLastnames() {
		return new String[][] {
			{ "Nola", "Lang" },
			{ "Marley", "Cole" },
			{ "Jack", "Davenport" },
			{ "Jocelyn", "Rich" },
			{ "Cameron", "Rosario" },
			{ "Jaime", "Morales" },
			{ "Evie", "Dudley" }
		};
	}
}
