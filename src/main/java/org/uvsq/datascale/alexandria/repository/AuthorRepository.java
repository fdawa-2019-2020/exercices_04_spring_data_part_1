package org.uvsq.datascale.alexandria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.uvsq.datascale.alexandria.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
