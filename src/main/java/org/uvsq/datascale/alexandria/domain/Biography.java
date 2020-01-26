package org.uvsq.datascale.alexandria.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Biography {
	
	@Id
	@GeneratedValue
	private Long id;

	private Date birthDate;
	private Date deathDate;
	private String birthCityName;
	private String deathCityName;
	private String text;
	
	@OneToOne
	private Author author;
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	public String getBirthCityName() {
		return birthCityName;
	}
	public void setBirthCityName(String birthCityName) {
		this.birthCityName = birthCityName;
	}
	public String getDeathCityName() {
		return deathCityName;
	}
	public void setDeathCityName(String deathCityName) {
		this.deathCityName = deathCityName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(birthCityName, birthDate, deathCityName, deathDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biography other = (Biography) obj;
		return Objects.equals(birthCityName, other.birthCityName) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(deathCityName, other.deathCityName) && Objects.equals(deathDate, other.deathDate);
	}
	
	
}
