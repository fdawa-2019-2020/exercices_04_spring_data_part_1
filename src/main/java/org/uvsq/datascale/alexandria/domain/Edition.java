package org.uvsq.datascale.alexandria.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Edition {
	
	@Id
	@GeneratedValue
	private Long id;
	private Date parutionDate;
	private String editorName;
	private Integer numberOfPage;
	private Boolean available;
	
	@ManyToOne
	private Book book;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getParutionDate() {
		return parutionDate;
	}
	public void setParutionDate(Date parutionDate) {
		this.parutionDate = parutionDate;
	}
	public String getEditorName() {
		return editorName;
	}
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}
	public Integer getNumberOfPage() {
		return numberOfPage;
	}
	public void setNumberOfPage(Integer numberOfPage) {
		this.numberOfPage = numberOfPage;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	@Override
	public int hashCode() {
		return Objects.hash(available, editorName, numberOfPage, parutionDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edition other = (Edition) obj;
		return Objects.equals(available, other.available) && Objects.equals(editorName, other.editorName)
				&& Objects.equals(numberOfPage, other.numberOfPage) && Objects.equals(parutionDate, other.parutionDate);
	}
	
	
	

}
