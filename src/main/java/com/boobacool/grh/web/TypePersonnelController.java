package com.boobacool.grh.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boobacool.grh.modeles.TypePersonnel;
import com.boobacool.grh.repository.TypePersonnelRepository;



@Controller
@RequestMapping(value="/tp")
public class TypePersonnelController {
	
	@Autowired
	private TypePersonnelRepository typePersonnelRepository;

	@RequestMapping(value = "/rajoutertp")
    public String tpPage(Model model, @RequestParam(defaultValue = "0") int page) {
       TypePersonnel tp= new TypePersonnel();
        tp.setId(0L);
        model.addAttribute("tp", tp);
        
        Page<TypePersonnel> listeTps= typePersonnelRepository.findAll(new PageRequest(page, 10));
        int pagescount = listeTps.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listeTps", listeTps);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "tp";
    }	
	
	@RequestMapping(value="saveTp",method=RequestMethod.POST)
	public String savePays(TypePersonnel tp,Model model) {
		typePersonnelRepository.save(tp);
		return "redirect:/tp/rajoutertp";
		
	}
	@RequestMapping(value="/supprimerTp")
	public String deleteTp(Long id)
	{
		typePersonnelRepository.delete(id);
		return "redirect:/tp/rajouterTp";
	}
	
	@RequestMapping(value="/modifierTp")
	public String editTp(TypePersonnel tp)
	{
		typePersonnelRepository.save(tp);
		
		return "redirect:/tp/rajoutertp";
	}
	
	
	@RequestMapping(value="/modifierTpR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		TypePersonnel tp= typePersonnelRepository.getOne(id);
        model.addAttribute("tp", tp);
        
        Page<TypePersonnel> listeTps= typePersonnelRepository.findAll(new PageRequest(page, 10));
        int pagescount = listeTps.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listeTps", listeTps);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "tp";
	}
}
