package org.uvsq.datascale.alexandria.repository;

import org.springframework.data.repository.CrudRepository;
import org.uvsq.datascale.alexandria.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long>{

}
