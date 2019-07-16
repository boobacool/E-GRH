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
import com.boobacool.grh.modeles.TypeConge;
import com.boobacool.grh.modeles.TypeContrat;
import com.boobacool.grh.repository.TypeCongeRepository;
import com.boobacool.grh.repository.TypeContratRepository;

@Controller
@RequestMapping(value="/tco")
public class TypeCongeController {
	
	
	@Autowired
	private TypeCongeRepository typeCongeRepository;
	
	
	@RequestMapping(value = "/rajoutertco")
    public String tcoPage(Model model, @RequestParam(defaultValue = "0") int page) {
       TypeConge tco= new TypeConge();
        tco.setId(0L);
        model.addAttribute("tco", tco);      
        Page<TypeConge> listetco= typeCongeRepository.findAll(new PageRequest(page, 10));
        int pagescount = listetco.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listetco", listetco);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "tco";
    }	
	
	@RequestMapping(value="savetco",method=RequestMethod.POST)
	public String saveTypeConge(TypeConge tco,Model model) {
		typeCongeRepository.save(tco);
		return "redirect:/tco/rajoutertco";
		
	}
	@RequestMapping(value="/supprimertco")
	public String deletetco(Long id)
	{
		typeCongeRepository.delete(id);
		return "redirect:/tco/rajoutertco";
	}
	
	@RequestMapping(value="/modifiertco")
	public String editsp(TypeConge tco)
	{
		typeCongeRepository.save(tco);
		
		return "redirect:/tco/rajoutertco";
	}
	
	
	@RequestMapping(value="/modifiertcoR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		TypeConge tco= typeCongeRepository.getOne(id);
        model.addAttribute("tco", tco);    
        Page<TypeConge> listetco= typeCongeRepository.findAll(new PageRequest(page, 10));
        int pagescount = listetco.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listetco", listetco);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "tco";
	}

}
