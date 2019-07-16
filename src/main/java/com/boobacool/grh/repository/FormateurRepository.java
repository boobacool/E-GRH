package com.boobacool.grh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boobacool.grh.modeles.Entreprise;
import com.boobacool.grh.modeles.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long>{
	
	List<Formateur> findByEntreprise(Entreprise entre);

}
