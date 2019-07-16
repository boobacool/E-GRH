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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="mission")
public class Mission implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date datedebut;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date datefin;
	@Min(1)
	private Integer nbjours;
	
	private Integer montant;
	@NotNull
	@Size(max=50)
	private String resultat;
	@NotNull
	private String description;
	
	private Integer etat;
	@ManyToOne
	@JoinColumn(name="personnel")
	private Personnel personnel;
	@ManyToOne
	@JoinColumn(name="typeMission")
	private TypeMission typeMission;
	
	public Mission() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Mission(Date datedebut, Date datefin, Integer nbjours, Personnel personnel, TypeMission typeMission) {
		super();
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.nbjours = nbjours;
		this.personnel = personnel;
		this.typeMission = typeMission;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public Integer getNbjours() {
		return nbjours;
	}

	public void setNbjours(Integer nbjours) {
		this.nbjours = nbjours;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public TypeMission getTypeMission() {
		return typeMission;
	}

	public void setTypeMission(TypeMission typeMission) {
		this.typeMission = typeMission;
	}



	public Integer getMontant() {
		return montant;
	}



	public void setMontant(Integer montant) {
		this.montant = montant;
	}



	public String getResultat() {
		return resultat;
	}



	public void setResultat(String resultat) {
		this.resultat = resultat;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getEtat() {
		return etat;
	}



	public void setEtat(Integer etat) {
		this.etat = etat;
	}
	
	

}
