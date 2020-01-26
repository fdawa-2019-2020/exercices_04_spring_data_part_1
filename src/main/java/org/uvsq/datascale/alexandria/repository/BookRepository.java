package org.uvsq.datascale.alexandria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uvsq.datascale.alexandria.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
