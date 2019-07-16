package com.boobacool.grh.modeles;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="entreprise")
public class Entreprise implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Size(min=2)
	private String raisonsociale;
	private String adresse;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
	private Date datecreation;
	@NotEmpty
	private String contact;
	private String typestructure;
	
	public Entreprise() {
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String raisonsociale, String adresse, Date datecreation, String contact, String typestructure) {
		super();
		this.raisonsociale = raisonsociale;
		this.adresse = adresse;
		this.datecreation = datecreation;
		this.contact = contact;
		this.typestructure = typestructure;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRaisonsociale() {
		return raisonsociale;
	}

	public void setRaisonsociale(String raisonsociale) {
		this.raisonsociale = raisonsociale;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTypestructure() {
		return typestructure;
	}

	public void setTypestructure(String typestructure) {
		this.typestructure = typestructure;
	}
}
