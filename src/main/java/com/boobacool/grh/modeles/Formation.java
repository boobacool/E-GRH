package com.boobacool.grh.modeles;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="formation")
public class Formation implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateformation;
	@NotEmpty
	private String theme;
	@ManyToOne
	@JoinColumn(name="formateur")
	private Formateur formateur;
	
	public Formation() {
		// TODO Auto-generated constructor stub
	}
	
	public Formation(Date dateformation, String theme, Formateur formateur) {
		
		this.dateformation = dateformation;
		this.theme = theme;
		this.formateur = formateur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateformation() {
		return dateformation;
	}

	public void setDateformation(Date dateformation) {
		this.dateformation = dateformation;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

}
