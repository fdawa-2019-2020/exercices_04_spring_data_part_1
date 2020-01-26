package org.uvsq.datascale.alexandria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uvsq.datascale.alexandria.domain.Edition;

public interface EditionRepository extends JpaRepository<Edition, Long>{

}
