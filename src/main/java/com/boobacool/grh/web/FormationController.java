package com.boobacool.grh.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boobacool.grh.modeles.Entreprise;
import com.boobacool.grh.modeles.Formateur;
import com.boobacool.grh.modeles.Formation;
import com.boobacool.grh.repository.EntrepriseRepository;
import com.boobacool.grh.repository.FormateurRepository;
import com.boobacool.grh.repository.FormationRepository;

@Controller
@RequestMapping(value="/formation")
public class FormationController {
	
	@Autowired
	private FormateurRepository formateurRepository;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private FormationRepository formationRepository;
	
	
	@RequestMapping(value = "/rajouterftion")
    public String formateurPage(Model model, @RequestParam(defaultValue = "0") int page) {
       Formation ftion= new Formation();
        ftion.setId(0L);
        model.addAttribute("ftion", ftion);
        List<Entreprise> listent=entrepriseRepository.findAll();
        model.addAttribute("listent", listent);
        List<Formateur> fteur= new ArrayList<>();
        model.addAttribute("fteur", fteur);
        Page<Formation> listeftions= formationRepository.findAll(new PageRequest(page, 10));
        int pagescount = listeftions.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listeftions", listeftions);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "formation";
    }	
	
	@RequestMapping(value="saveftion",method=RequestMethod.POST)
	public String saveFormation(Formation ftion,Model model) {
		formationRepository.save(ftion);
		return "redirect:/formation/rajouterftion";
		
	}
	@RequestMapping(value="/supprimerftion")
	public String deleteFormation(Long id)
	{
		formationRepository.delete(id);
		return "redirect:/formation/rajouterftion";
	}
	
	@RequestMapping(value="/modifierftion")
	public String editFormateur(Formation ftion)
	{
		formationRepository.save(ftion);
		
		return "redirect:/formation/rajouterftion";
	}
	
	
	@RequestMapping(value="/modifierftionR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Formation ftion= formationRepository.getOne(id);
        model.addAttribute("ftion", ftion);
        List<Entreprise> listent=entrepriseRepository.findAll();
        model.addAttribute("listent", listent);
        List<Formateur> fteur= new ArrayList<>();
        model.addAttribute("fteur", fteur);
        Page<Formation> listeftions= formationRepository.findAll(new PageRequest(page, 10));
        int pagescount = listeftions.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listeftions", listeftions);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "formation";
	}

}
