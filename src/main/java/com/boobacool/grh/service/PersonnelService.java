package com.boobacool.grh.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.boobacool.grh.modeles.Personnel;

public interface PersonnelService {
	
	public void ajouter(Personnel p);
	public void modifier(Personnel p);
	public void supprimer(Long id);
	public Page<Personnel> listep();

}
