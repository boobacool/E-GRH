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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="personnel")
public class Personnel implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "remplissez le champ matricule")
	 @NotNull
	 @Size(min=5, max=100,message="Erreur matricule :Nombre caractères entre 5 et 100 ")
	private String matricule;
	@Size(max=100)
	private String nom;
	@Size(max=100)
	private String prenom;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date datenaiss;
	@Size(max=100)
	private String lieunaiss;
	@Size(max=20)
	private String contact;
	
	private int nbreannee;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateentree;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date datesortie;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="typePersonnel")
	private TypePersonnel typePersonnel;
	@ManyToOne
	@JoinColumn(name="service")
	private Service service;
	
	@ManyToOne
	@JoinColumn(name="specialite")
	private Specialite specialite;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="antenne")
	private Antenne antenne;
	
	public Personnel() {
		// TODO Auto-generated constructor stub
	}

	public Personnel(String matricule, String nom, String prenom, Date datenaiss, String lieunaiss, String contact,
			TypePersonnel typePersonnel, Service service, Specialite specialite, Antenne antenne) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.datenaiss = datenaiss;
		this.lieunaiss = lieunaiss;
		this.contact = contact;
		this.typePersonnel = typePersonnel;
		this.service = service;
		this.specialite = specialite;
		this.antenne = antenne;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDatenaiss() {
		return datenaiss;
	}

	public void setDatenaiss(Date datenaiss) {
		this.datenaiss = datenaiss;
	}

	public String getLieunaiss() {
		return lieunaiss;
	}

	public void setLieunaiss(String lieunaiss) {
		this.lieunaiss = lieunaiss;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public TypePersonnel getTypePersonnel() {
		return typePersonnel;
	}

	public void setTypePersonnel(TypePersonnel typePersonnel) {
		this.typePersonnel = typePersonnel;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public Antenne getAntenne() {
		return antenne;
	}

	public void setAntenne(Antenne antenne) {
		this.antenne = antenne;
	}

	public Date getDateentree() {
		return dateentree;
	}

	public void setDateentree(Date dateentree) {
		this.dateentree = dateentree;
	}

	public int getNbreannee() {
		return nbreannee;
	}

	public void setNbreannee(int nbreannee) {
		this.nbreannee = nbreannee;
	}

	public Date getDatesortie() {
		return datesortie;
	}

	public void setDatesortie(Date datesortie) {
		this.datesortie = datesortie;
	}
	
	
	
	

}
