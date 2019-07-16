package com.boobacool.grh.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boobacool.grh.modeles.Entreprise;
import com.boobacool.grh.modeles.Formateur;
import com.boobacool.grh.repository.EntrepriseRepository;
import com.boobacool.grh.repository.FormateurRepository;
import com.boobacool.grh.repository.SpecialiteRepository;

@RestController
public class RsetFullApplication {

	
	@Autowired
	private FormateurRepository formateurRepository;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private SpecialiteRepository specialiteRepository;
	
	@RequestMapping(value="/fteurId1/",method=RequestMethod.GET)
	public List<Formateur> listeFormateur(@PathParam("id") Long id){
		Entreprise entre=entrepriseRepository.getOne(id);
		List<Formateur> liste= formateurRepository.findByEntreprise(entre);
		return liste;
	}
}
