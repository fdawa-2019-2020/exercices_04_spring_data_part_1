package org.uvsq.datascale.alexandria.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uvsq.datascale.alexandria.domain.Author;
import org.uvsq.datascale.alexandria.domain.Biography;
import org.uvsq.datascale.alexandria.repository.AuthorRepository;
import org.uvsq.datascale.alexandria.repository.BiographyRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AuthorRepositoryTestIT {
	
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BiographyRepository biographyRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	
	
	@Test
	void testSavedAndReturnedIsTheSame() {
		// given
		Author author = new Author();
		
		// when
		Author returnedAuthor = authorRepository.save(author);
		
		// then
		assertThat(authorRepository.count()).isEqualTo(1);
		assertThat(returnedAuthor).isSameAs(author);
		assertThat(returnedAuthor.getId()).isNotNull();
	}
	
	
	
	@Test
	void testDBisMirroringMissingAuthorInBiography() throws ParseException {
		// given
		Author author = new Author();
		author.setFirstname("John");
		author.setLastname("DOE");

		Biography biography = new Biography();
		biography.setBirthCityName("NEW-YORK");
		biography.setBirthDate(simpleDateFormat.parse("1901-12-31"));
		biography.setDeathCityName("Paris");
		biography.setDeathDate(simpleDateFormat.parse("1991-1-1"));
		biography.setText("He lived!");
		
		// when
		
		author.setBiography(biography);
		biography.setAuthor(author);
		biographyRepository.save(biography);
		authorRepository.saveAndFlush(author);	
		
		// then
		assertThat(biography.getAuthor()).isNotNull();
		
		int nbOfBiographyRecord = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM biography", Integer.class);
		assertThat(nbOfBiographyRecord).isEqualTo(1);
		
		Map<String, Object> result = jdbcTemplate.queryForMap("SELECT * FROM biography");
		assertThat(result.get("DEATH_CITY_NAME")).isEqualTo("Paris");
		assertThat(result.get("AUTHOR_ID")).isNotNull();
	}

}
