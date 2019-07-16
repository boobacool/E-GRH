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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="conge")
public class Conge implements Serializable{
	
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
	@ManyToOne
	@JoinColumn(name="personnel")
	private Personnel personnel;
	@ManyToOne
	@JoinColumn(name="typeConge")
	private TypeConge typeConge;
	
	public Conge() {
		// TODO Auto-generated constructor stub
	}

	public Conge(Date datedebut, Date datefin, Integer nbjours, Personnel personnel, TypeConge typeConge) {
		super();
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.nbjours = nbjours;
		this.personnel = personnel;
		this.typeConge = typeConge;
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

	public TypeConge getTypeConge() {
		return typeConge;
	}

	public void setTypeConge(TypeConge typeConge) {
		this.typeConge = typeConge;
	}

}
