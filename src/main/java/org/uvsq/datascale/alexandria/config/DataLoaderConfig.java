package org.uvsq.datascale.alexandria.config;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.uvsq.datascale.alexandria.domain.Author;
import org.uvsq.datascale.alexandria.domain.Biography;
import org.uvsq.datascale.alexandria.repository.AuthorRepository;

@Configuration
public class DataLoaderConfig {

	private static Logger logger = LoggerFactory.getLogger(DataLoaderConfig.class);
	
	@Bean
	public ApplicationRunner authorLoader(AuthorRepository authorRepository) {
		return new ApplicationRunner() {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			@Override
			@Transactional
			public void run(ApplicationArguments args) throws Exception {
				logger.info("Run author initialization");
				Author victor = new Author();
				victor.setFirstname("Victor");
				victor.setLastname("Hugo");
				Biography biography = new Biography();
				biography.setBirthCityName("BESANCON");
				biography.setBirthDate(simpleDateFormat.parse("1802-1-26"));
				biography.setDeathCityName("Paris");
				biography.setDeathDate(simpleDateFormat.parse("1885-05-22"));
				biography.setText("He lived!");
				victor.setBiography(biography);

				saveAuthor(victor, authorRepository);
			}
		};
	}

	protected void saveAuthor(Author victor, AuthorRepository repository) {
		repository.save(victor);
	}
}
