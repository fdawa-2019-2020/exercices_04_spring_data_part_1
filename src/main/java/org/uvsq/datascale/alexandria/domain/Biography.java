package org.uvsq.datascale.alexandria.domain;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Biography {

	private Date birthDate;
	private Date deathDate;
	private String birthCityName;
	private String deathCityName;
	private String text;
	
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
	
	
}
