package com.boobacool.grh.modeles;

public class Nbre {
	private Personnel personnel;
	private int nombre;
	public Nbre() {
		
	}
	
	
	public Nbre(Personnel personnel, int nombre) {
		this.personnel = personnel;
		this.nombre = nombre;
	}


	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
	
	

}
