package org.uvsq.datascale.alexandria.domain;

import java.util.Date;

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
	private Date birthDate;
	
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date date) {
		this.birthDate = date;
	}
	public Long getId() {
		return id;
	}
}
