package org.uvsq.datascale.alexandria.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Author {

	@Id
	@GeneratedValue
	private Long id;
	private String firstname;
	private String lastname;

	@OneToOne
	private Biography biography;

	@ManyToMany
	private Set<Book> books = new HashSet<>();

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Biography getBiography() {
		return biography;
	}

	public void setBiography(Biography biography) {
		this.biography = biography;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(biography, books, firstname, lastname);
	}
	
	public Set<Book> getBooks() {
		return books;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(biography, other.biography) && Objects.equals(books, other.books)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname);
	}



}
