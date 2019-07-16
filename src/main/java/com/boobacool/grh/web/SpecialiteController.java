package com.boobacool.grh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boobacool.grh.modeles.Specialite;
import com.boobacool.grh.repository.SpecialiteRepository;

@Controller
@RequestMapping(value="/sp")
public class SpecialiteController {
	
	@Autowired
	private SpecialiteRepository specialiteRepository;
	
	
	@RequestMapping(value = "/rajoutersp")
    public String specialitePage(Model model, @RequestParam(defaultValue = "0") int page) {
       Specialite sp= new Specialite();
        sp.setId(0L);
        model.addAttribute("sp", sp);      
        Page<Specialite> listesp= specialiteRepository.findAll(new PageRequest(page, 10));
        int pagescount = listesp.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listesp", listesp);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "sp";
    }	
	
	@RequestMapping(value="savesp",method=RequestMethod.POST)
	public String saveSpecialite(Specialite sp,Model model) {
		specialiteRepository.save(sp);
		return "redirect:/sp/rajoutersp";
		
	}
	@RequestMapping(value="/supprimersp")
	public String deleteSpecialite(Long id)
	{
		specialiteRepository.delete(id);
		return "redirect:/sp/rajoutersp";
	}
	
	@RequestMapping(value="/modifieran")
	public String editsp(Specialite sp)
	{
		specialiteRepository.save(sp);
		
		return "redirect:/sp/rajoutersp";
	}
	
	
	@RequestMapping(value="/modifierspR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Specialite sp= specialiteRepository.getOne(id);
        model.addAttribute("sp", sp);    
        Page<Specialite> listesp= specialiteRepository.findAll(new PageRequest(page, 10));
        int pagescount = listesp.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listesp", listesp);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "sp";
	}

}
