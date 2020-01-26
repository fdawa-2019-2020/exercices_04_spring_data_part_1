package org.uvsq.datascale.alexandria.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	
	@OneToMany
	private Set<Edition> editions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Edition> getEditions() {
		return editions;
	}

	public void setEditions(Set<Edition> editions) {
		this.editions = editions;
	}
	
	
}