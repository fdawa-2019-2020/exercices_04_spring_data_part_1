package org.uvsq.datascale.alexandria.domain;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {
	
	@Id
	@GeneratedValue
	private Long id;
	private String firstname;
	private String lastname;
	
	@Embedded
	private Biography biography;
	
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
	
}
