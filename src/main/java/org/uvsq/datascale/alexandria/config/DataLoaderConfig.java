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

@Configuration
public class DataLoaderConfig {

	private static Logger logger = LoggerFactory.getLogger(DataLoaderConfig.class);
	
	@Bean
	public ApplicationRunner authorLoader(EntityManager entityManager) {
		return new ApplicationRunner() {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			@Override
			@Transactional
			public void run(ApplicationArguments args) throws Exception {
				logger.info("Run author initialization");
				Author victor = new Author();
				victor.setFirstname("Victor");
				victor.setLastname("Hugo");
				victor.setBirthDate(simpleDateFormat.parse("1802-02-26"));
				saveAuthor(victor, entityManager);
			}
		};
	}

	protected void saveAuthor(Author victor, EntityManager entityManager) {
		entityManager.persist(victor);
	}
}
