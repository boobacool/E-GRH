package com.boobacool.grh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boobacool.grh.modeles.Entreprise;
import com.boobacool.grh.repository.EntrepriseRepository;

@Controller
@RequestMapping(value="/entreprise")
public class EntrepriseController {
	
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	
	@RequestMapping(value = "/rajouterent")
    public String tcoPage(Model model, @RequestParam(defaultValue = "0") int page) {
       Entreprise  ent= new Entreprise();
        ent.setId(0L);
        model.addAttribute("ent", ent);      
        Page<Entreprise> listent= entrepriseRepository.findAll(new PageRequest(page, 10));
        int pagescount = listent.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listent", listent);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "entreprise";
    }	
	
	@RequestMapping(value="saveent",method=RequestMethod.POST)
	public String saveEntreprise(Entreprise ent,Model model) {
		entrepriseRepository.save(ent);
		return "redirect:/entreprise/rajouterent";
		
	}
	@RequestMapping(value="/supprimerent")
	public String deleteEntreprise(Long id)
	{
		entrepriseRepository.delete(id);
		return "redirect:/entreprise/rajouterent";
	}
	
	@RequestMapping(value="/modifierent")
	public String edittm(Entreprise ent)
	{
		entrepriseRepository.save(ent);
		
		return "redirect:/entreprise/rajouterent";
	}
	
	
	@RequestMapping(value="/modifierentR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Entreprise ent= entrepriseRepository.getOne(id);
        model.addAttribute("ent", ent);    
        Page<Entreprise> listent= entrepriseRepository.findAll(new PageRequest(page, 10));
        int pagescount = listent.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listent", listent);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "entreprise";
	}

}
