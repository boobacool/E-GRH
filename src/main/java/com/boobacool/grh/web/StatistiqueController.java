package com.boobacool.grh.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.ls.LSInput;

import com.boobacool.grh.modeles.Conge;
import com.boobacool.grh.modeles.Mission;
import com.boobacool.grh.modeles.Nbre;
import com.boobacool.grh.modeles.Personnel;
import com.boobacool.grh.repository.CongeRepository;
import com.boobacool.grh.repository.ContratRepository;
import com.boobacool.grh.repository.MissionRepository;
import com.boobacool.grh.repository.PersonnelRepository;

@Controller

public class StatistiqueController {
	@Autowired
	private ContratRepository contratRepository;
	@Autowired
	private CongeRepository congeRepository;
	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	
	
	@RequestMapping(value="/")
	public String index() {
		
		return "redirect:/accueil";
	}
	
	
	@RequestMapping(value="/accueil")
	public String page(Model model) {
		
		int nbc=nbreContratActives();
		model.addAttribute("nbc", nbc);
		int nbp=personnelRepository.findAll().size();
		model.addAttribute("nbp", nbp);
		int nbco=contratRepository.findAll().size();
		model.addAttribute("nbco", nbco);
		int nbcong=congeRepository.findAll().size();
		model.addAttribute("nbcong", nbcong);
		return "accueil";
	}
	
	
	
	
	public int nbreContratActives() {
		int nb=contratRepository.listeActive().size();
		return nb;
	}
	
	@RequestMapping(value="/nbcongep")
	public String nbreCongeParPers(Model model,@RequestParam int annee) {
		
		List<Personnel> listep=personnelRepository.findAll();
		List<Nbre> listenbre=new ArrayList<>();
		for(Personnel p:listep) {
		List<Conge> listec=	congeRepository.listeCongeParPers(p.getId(), annee);
		if(listec.size()>0) {
			Nbre n= new Nbre(p,listec.size());
			listenbre.add(n);
		}
		}
		model.addAttribute("listenbre",listenbre);
		
		
		return "listeNbc";
	}
	
	@RequestMapping(value="/nbmp")
	public String nbreMissionParPers(Model model,@RequestParam int annee) {
		List<Personnel> listep=personnelRepository.findAll();
		List<Nbre> listenbre=new ArrayList<>();
		for(Personnel p:listep) {
		List<Mission> listem=	missionRepository.listeMissionParPers(p.getId(), annee);
		if(listem.size()>0) {
			Nbre n= new Nbre(p,listem.size());
			listenbre.add(n);
		}
		}
		
		model.addAttribute("listenbre",listenbre);
		
		return "listeNbm";
	}
	
	
	public int nbreMissionParAn(int annee) {
		int nbre=missionRepository.findAll().size();
		return nbre;
	}
	
	

}
